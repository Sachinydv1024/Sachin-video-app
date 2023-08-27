package com.example.videocallapp.ui.allFragmnet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.videocallapp.R
import io.agora.rtc2.RtcEngine

class LiveCallFragment : Fragment() {

    var appCertificate = "b5065fbfa5ed4d8aba0c25de974502b1"
    var expirationTimeInSeconds = 3600
    private val channelName = "papayacoders"

    private var token: String? = null
    private val uid = 0
    private var isJoined = false

    private var agoraEngine: RtcEngine? = null

    private var localSurfaceView: SurfaceView? = null

    private var remoteSurfaceView: SurfaceView? = null


    private val PERMISSION_REQ_ID = 22
    private val REQUESTED_PERMISSIONS = arrayOf<String>(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA
    )

    private fun checkSelfPermission(): Boolean {
        return !(context?.let {
            ContextCompat.checkSelfPermission(
                it,
                REQUESTED_PERMISSIONS[0]
            )
        } != PackageManager.PERMISSION_GRANTED ||
                context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        REQUESTED_PERMISSIONS[1]
                    )
                } != PackageManager.PERMISSION_GRANTED)
    }

//    fun showMessage(message: String?) {
//        context.runOnUiThread {
//            Toast.makeText(
//                applicationContext,
//                message,
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_call, container, false)
    }


}