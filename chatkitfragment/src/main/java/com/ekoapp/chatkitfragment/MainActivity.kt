package com.ekoapp.chatkitfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ekoapp.ekosdk.EkoChannel
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.home.callback.IRecentChatClickListener
import com.ekoapp.ekosdk.uikit.chat.home.fragment.EkoChatHomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IRecentChatClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        EkoClient.registerDevice("testUser2", "Test User2").subscribe()

        btnLoadFragment.setOnClickListener {
            initializeFragment()
            btnLoadFragment.visibility = View.GONE
        }

    }

    private fun initializeFragment() {
        /**
         * use Fragment builder to create Instance
         */
        val chatHomeFragment = EkoChatHomeFragment.Builder().build()
        /**
         * set the listener to override recentItem click event
         * No Need to implement [IRecentChatClickListener] if you don't want to override click event
         */
        chatHomeFragment.setRecentChatItemClickListener(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, chatHomeFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRecentChatItemClick(channelId: String) {
        /**
         * Use click event for custom action
         */
    }
}
