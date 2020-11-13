package com.ekoapp.chatkitrecentchatfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.home.callback.IRecentChatItemClickListener
import com.ekoapp.ekosdk.uikit.chat.recent.fragment.EkoRecentChatFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IRecentChatItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Replace with actual userId[String] and displayName[String]
         */
        EkoClient.registerDevice("testUser2").displayName("Test User2").build().submit().subscribe()

        btnLoadFragment.setOnClickListener {
            initializeFragment()
            btnLoadFragment.visibility = View.GONE
        }

    }

    private fun initializeFragment() {
        /**
         * use Fragment builder to create Instance
         */
        val recentChatFragment = EkoRecentChatFragment.Builder()
            /**
             * set the listener to override recentItem click event
             * No Need to implement [IRecentChatClickListener] if you don't want to override click event
             */
            .recentChatItemClickListener(this)
            .build(this)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, recentChatFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRecentChatItemClick(channelId: String) {
        /**
         * Use click event for custom action
         */
    }
}
