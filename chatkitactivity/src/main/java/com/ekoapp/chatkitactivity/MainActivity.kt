package com.ekoapp.chatkitactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.home.EkoChatHomePageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        EkoClient.registerDevice("testUser2").displayName("Test User2").build().submit().subscribe()

        btnChatActivity.setOnClickListener {
            val chatIntent  = Intent(this, EkoChatHomePageActivity::class.java)
            startActivity(chatIntent)
        }
    }
}
