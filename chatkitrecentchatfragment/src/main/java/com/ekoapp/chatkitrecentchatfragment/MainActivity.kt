package com.ekoapp.chatkitrecentchatfragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.sdk.AmityCoreClient
import com.amity.socialcloud.uikit.chat.home.callback.AmityRecentChatItemClickListener
import com.amity.socialcloud.uikit.chat.recent.fragment.AmityRecentChatFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AmityRecentChatItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Replace with actual userId[String] and displayName[String]
         */
        AmityCoreClient.registerDevice("testUser2").displayName("Test User2").build().submit()
            .subscribe()

        btnLoadFragment.setOnClickListener {
            initializeFragment()
            btnLoadFragment.visibility = View.GONE
        }

    }

    private fun initializeFragment() {
        /**
         * use Fragment builder to create Instance
         */
        val recentChatFragment = AmityRecentChatFragment.newInstance()
            /**
             * set the listener to override recentItem click event
             * No Need to implement [AmityRecentChatItemClickListener] if you don't want to override click event
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
