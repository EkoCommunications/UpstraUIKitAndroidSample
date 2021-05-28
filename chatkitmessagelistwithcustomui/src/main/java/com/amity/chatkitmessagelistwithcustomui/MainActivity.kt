package com.amity.chatkitmessagelistwithcustomui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.sdk.AmityCoreClient
import com.amity.socialcloud.uikit.chat.home.callback.AmityRecentChatFragmentDelegate
import com.amity.socialcloud.uikit.chat.home.callback.AmityRecentChatItemClickListener
import com.amity.socialcloud.uikit.chat.home.fragment.AmityChatHomePageFragment
import com.amity.socialcloud.uikit.chat.recent.fragment.AmityRecentChatFragment
import com.ekoapp.chatkitmessagelistwithcustomui.R

class MainActivity : AppCompatActivity(), AmityRecentChatFragmentDelegate,
    AmityRecentChatItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        AmityCoreClient.registerDevice("testUser2").displayName("Test User2").build().submit()
            .subscribe()

        val chatHomeFragment = AmityChatHomePageFragment.newInstance()
            /**
             * Implement listener to override item click
             * No need to implement [AmityRecentChatItemClickListener] if you don't want to override item click
             */
            .recentChatFragmentDelegate(this)
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

    override fun recentChatFragment(): AmityRecentChatFragment {
        return AmityRecentChatFragment.newInstance()
            .recentChatItemClickListener(this)
            .build(this)
    }
}
