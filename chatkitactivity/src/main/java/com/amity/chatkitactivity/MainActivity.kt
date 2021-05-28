package com.amity.chatkitactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.sdk.AmityCoreClient
import com.amity.socialcloud.uikit.chat.home.AmityChatHomePageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        AmityCoreClient.registerDevice("testUser2").displayName("Test User2").build().submit()
            .subscribe()

        btnChatActivity.setOnClickListener {
            val chatIntent = Intent(this, AmityChatHomePageActivity::class.java)
            startActivity(chatIntent)
        }
    }
}
