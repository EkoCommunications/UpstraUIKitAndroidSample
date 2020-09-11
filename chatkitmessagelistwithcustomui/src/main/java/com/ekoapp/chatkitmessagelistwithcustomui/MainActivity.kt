package com.ekoapp.chatkitmessagelistwithcustomui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.home.callback.IRecentChatClickListener
import com.ekoapp.ekosdk.uikit.chat.home.fragment.EkoChatHomeFragment

class MainActivity : AppCompatActivity(), IRecentChatClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        EkoClient.registerDevice("testUser2", "Test User2").subscribe()

        val chatHomeFragment = EkoChatHomeFragment.Builder().build()
        chatHomeFragment.setRecentChatItemClickListener(this)
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
