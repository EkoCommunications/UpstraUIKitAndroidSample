package com.amity.chatkitfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.sdk.AmityCoreClient
import com.amity.socialcloud.uikit.chat.home.callback.AmityRecentChatFragmentDelegate
import com.amity.socialcloud.uikit.chat.home.callback.AmityRecentChatItemClickListener
import com.amity.socialcloud.uikit.chat.home.fragment.AmityChatHomePageFragment
import com.amity.socialcloud.uikit.chat.recent.fragment.AmityRecentChatFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AmityRecentChatFragmentDelegate {

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
        val chatHomeFragment = AmityChatHomePageFragment.newInstance()
            /**
             * set the listener to override recentItem click event
             * No Need to implement [AmityRecentChatItemClickListener] if you don't want to override click event
             */
            .recentChatFragmentDelegate(this)
            .build(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, chatHomeFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun recentChatFragment(): AmityRecentChatFragment {
        return AmityRecentChatFragment.newInstance()
            .recentChatItemClickListener(object : AmityRecentChatItemClickListener {
                override fun onRecentChatItemClick(channelId: String) {
                    /**
                     * Use click event for custom action
                     */
                    Toast.makeText(this@MainActivity, "RecentChatItem Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            .build(this)
    }
}
