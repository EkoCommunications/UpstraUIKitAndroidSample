package com.ekoapp.chatkitmessagelistwithcustomui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.home.callback.IRecentChatItemClickListener
import com.ekoapp.ekosdk.uikit.chat.home.fragment.EkoChatHomePageFragment

class MainActivity : AppCompatActivity(), IRecentChatItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        EkoClient.registerDevice("testUser2").displayName("Test User2").build().submit().subscribe()

        val chatHomeFragment = EkoChatHomePageFragment.Builder()
            /**
             * Implement listener to override item click
             * No need to implement [IRecentChatClickListener] if you don't want to override item click
             */
            .recentChatItemClickListener(this)
            .build(this)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, chatHomeFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRecentChatItemClick(channelId: String) {
        val intent = Intent(this, MessageListActivity::class.java).apply {
            putExtra("CHANNEL_ID", channelId)
        }
        startActivity(intent)
    }
}
