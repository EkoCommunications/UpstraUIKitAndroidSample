package com.ekoapp.chatkitmessagelistactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.messages.EkoMessageListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        EkoClient.registerDevice("testUser2").displayName("Test User2").build().submit().subscribe()
        loadMessageList()
    }

    private fun loadMessageList() {
        btnMessageList.setOnClickListener {
            /**
             * Use [EkoMessageListActivity.newIntent] to create EkoMessageList intent
             * Replace channelId[String] with actual value
             */
            val messageIntent = EkoMessageListActivity.newIntent(this, "channelId")
            startActivity(messageIntent)
        }
    }
}
