package com.amity.community.newsfeed

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.amity.community.R
import com.amity.socialcloud.sdk.AmityCoreClient
import com.amity.socialcloud.uikit.community.explore.fragments.AmityCommunityExplorerFragment
import com.amity.socialcloud.uikit.community.newsfeed.fragment.AmityMyFeedFragment
import com.amity.socialcloud.uikit.community.newsfeed.fragment.AmityNewsFeedFragment
import com.amity.socialcloud.uikit.community.profile.fragment.AmityUserProfilePageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_news_feed_home.*
import java.util.*

class NewsFeedHomeActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed_home)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = getNewsFeed()
        addFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.globalFeed -> {
                    supportActionBar?.title = "News Feed"
                    val fragment = getNewsFeed()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.myTimeline -> {
                    supportActionBar?.title = "My Timeline"
                    val fragment = getTimeline()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.explore -> {
                    supportActionBar?.title = "Explore"
                    val fragment = getExplore()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun getNewsFeed(): Fragment {
        return AmityNewsFeedFragment.newInstance().build(this)
    }

    private fun getProfile(): Fragment {
        return AmityUserProfilePageFragment.newInstance(AmityCoreClient.getUserId()).build(this)
    }

    private fun getExplore(): Fragment {
        return AmityCommunityExplorerFragment.newInstance().build(this)
    }

    private fun getTimeline(): Fragment {
        return AmityMyFeedFragment.newInstance().build(this)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }
}