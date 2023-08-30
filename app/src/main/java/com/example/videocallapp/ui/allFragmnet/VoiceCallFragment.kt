package com.example.videocallapp.ui.allFragmnet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.videocallapp.databinding.FragmentVoiceCallBinding
import com.example.videocallapp.model.agora.media.RtcTokenBuilder2
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig

class VoiceCallFragment : Fragment() {
    private lateinit var binding: FragmentVoiceCallBinding

    private val PERMISSION_REQ_ID = 22
    private val REQUESTED_PERMISSIONS = arrayOf<String>(
        Manifest.permission.RECORD_AUDIO,
    )


    private fun checkSelfPermission(): Boolean {
        return activity?.let {
            ContextCompat.checkSelfPermission(
                it,
                REQUESTED_PERMISSIONS[0]
            )
        } == PackageManager.PERMISSION_GRANTED
    }

    var expirationTimeInSeconds = 3600

    // Fill the App ID of your project generated on Agora Console.
    private val appId = "5fb926599aeb4ba391c29247cc3b6f71"

    // Fill the channel name.
    private val channelName = "sumitrai"

    var appCertificate = "b5065fbfa5ed4d8aba0c25de974502b1"

    // Fill the temp token generated on Agora Console.
    private var token: String? = null
//    private val token =
//        "007eJxTYDDp3SWzb5uimNhB/V+mL5lvLmFi/xjmUq06+8V/76bHa+YoMJimJVkamZlaWiamJpkkJRpbGiYbWRqZmCcnGyeZpZkbKnYuTG4IZGRgnyjPxMgAgSA+D0NBYkFiZWJyfkpqUTEDAwAi+yGx"

    // An integer that identifies the local user.
    private val uid = 0

    // Track the status of your connection
    private var isJoined = false

    // Agora engine instance
    private var agoraEngine: RtcEngine? = null


    fun showMessage(message: String?) {
        activity?.runOnUiThread {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_voice_call, container, false)
        binding = FragmentVoiceCallBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tokenBuilder =
            RtcTokenBuilder2()
        val timestamp = (System.currentTimeMillis() / 1000 + expirationTimeInSeconds).toInt()

        println("UID token")
        val result = tokenBuilder.buildTokenWithUid(
            appId, appCertificate,
            channelName, uid, RtcTokenBuilder2.Role.ROLE_PUBLISHER, timestamp, timestamp
        )
        println(result)


        token = result

        if (!checkSelfPermission()) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    REQUESTED_PERMISSIONS,
                    PERMISSION_REQ_ID
                )
            };
        }

        setupVoiceSDKEngine();
        binding.apply {
            joinLeaveButton.setOnClickListener {
                joinLeaveChannel()
            }
        }
    }

    private fun setupVoiceSDKEngine() {
        try {
            val config = RtcEngineConfig()
            config.mContext = context
            config.mAppId = appId
            config.mEventHandler = mRtcEventHandler
            agoraEngine = RtcEngine.create(config)
        } catch (e: Exception) {
            throw RuntimeException("Check the error.")
        }
    }


    fun joinLeaveChannel() {
        if (isJoined) {
            agoraEngine!!.leaveChannel()
           // binding.joinLeaveButton!!.text = "Join"
        } else {
            joinChannel()
          // binding.joinLeaveButton!!.text = "Leave"
        }
    }


    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        // Listen for the remote user joining the channel.
        override fun onUserJoined(uid: Int, elapsed: Int) {
            activity?.runOnUiThread { binding.infoText!!.text = "Remote user joined: $uid" }
        }

        override fun onJoinChannelSuccess(channel: String, uid: Int, elapsed: Int) {
            // Successfully joined a channel
            isJoined = true
            showMessage("Joined Channel $channel")

            activity?.runOnUiThread {
                binding.infoText!!.text = "Waiting for a remote user to join"
            }
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            // Listen for remote users leaving the channel
            showMessage("Remote user offline $uid $reason")
            if (isJoined)
                activity?.runOnUiThread {
                    binding.infoText!!.text = "Waiting for a remote user to join"
                }
        }

        override fun onLeaveChannel(stats: RtcStats) {
            // Listen for the local user leaving the channel
            activity?.runOnUiThread {
                binding.infoText!!.text = "Press the button to join a channel"
            }
            isJoined = false
        }
    }

    private fun joinChannel() {
        val options = ChannelMediaOptions()
        options.autoSubscribeAudio = true
        // Set both clients as the BROADCASTER.
        options.clientRoleType = Constants.CLIENT_ROLE_BROADCASTER
        // Set the channel profile as BROADCASTING.
        options.channelProfile = Constants.CHANNEL_PROFILE_LIVE_BROADCASTING

        // Join the channel with a temp token.
        // You need to specify the user ID yourself, and ensure that it is unique in the channel.
        agoraEngine!!.joinChannel(token, channelName, uid, options)
    }


    override fun onDestroy() {
        super.onDestroy()
        agoraEngine?.leaveChannel()

        // Destroy the engine in a sub-thread to avoid congestion
        Thread {
            RtcEngine.destroy()
            agoraEngine = null
        }.start()
    }

}