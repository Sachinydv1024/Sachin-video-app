package com.example.videocallapp.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.videocallapp.R
import io.agora.ConnectionListener
import io.agora.chat.ChatClient
import io.agora.chat.ChatMessage
import io.agora.chat.ChatOptions
import io.agora.chat.TextMessageBody

class ChatActivity : AppCompatActivity() {

    private val USERNAME = ""

    // Gets token from Agora Console or generates by your app server
    private val TOKEN = ""

    // Gets AppKey from Agora Console
    private val APP_KEY = "5fb926599aeb4ba391c29247cc3b6f71"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initView();
        initSDK();
        initListener();
    }

    private fun initView() {
        (findViewById<TextView>(R.id.tv_log) as TextView).movementMethod = ScrollingMovementMethod()
    }

    // Initializes the SDK.
    private fun initSDK() {
        val options = ChatOptions()
        // Gets your App Key from Agora Console.
        if (TextUtils.isEmpty(APP_KEY)) {
            Toast.makeText(
                this@ChatActivity,
                "You should set your AppKey first!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        // Sets your App Key to options.
        options.appKey = APP_KEY
        // Initializes the Agora Chat SDK.
        ChatClient.getInstance().init(this, options)
        // Makes the Agora Chat SDK debuggable.
        ChatClient.getInstance().setDebugMode(true)
        // Shows the current user.
        (findViewById<TextView>(R.id.et_to_username) as TextView).text = "Current user: $USERNAME"
    }


    private fun initListener() {
        // Adds message event callbacks.
        ChatClient.getInstance().chatManager().addMessageListener { messages: List<ChatMessage> ->
            for (message in messages) {
                val builder = StringBuilder()
                builder.append("Receive a ").append(message.type.name)
                    .append(" message from: ").append(message.from)
                if (message.type == ChatMessage.Type.TXT) {
                    builder.append(" content:")
                        .append((message.body as TextMessageBody).message)
                }
                // showLog(builder.toString(), false)
            }
        }
        // Adds connection event callbacks.
        ChatClient.getInstance().addConnectionListener(object : ConnectionListener {
            override fun onConnected() {
                //showLog("onConnected", false)
            }

            override fun onDisconnected(error: Int) {
                //showLog("onDisconnected: $error", false)
            }

            override fun onLogout(errorCode: Int) {
                // showLog("User needs to log out: $errorCode", false)
                ChatClient.getInstance().logout(false, null)
            }

            // This callback occurs when the token expires. When the callback is triggered, the app client must get a new token from the app server and logs in to the app again.
            override fun onTokenExpired() {
                // showLog("ConnectionListener onTokenExpired", true)
            }

            // This callback occurs when the token is about to expire.
            override fun onTokenWillExpire() {
                // showLog("ConnectionListener onTokenWillExpire", true)
            }
        })
    }
}