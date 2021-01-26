package com.ekoapp.community.newsfeed

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekoapp.community.R
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.community.explore.fragments.EkoExploreFragment
import com.ekoapp.ekosdk.uikit.community.newsfeed.fragment.EkoFeedFragment
import com.ekoapp.ekosdk.uikit.community.newsfeed.fragment.EkoNewsFeedFragment
import com.ekoapp.ekosdk.uikit.community.profile.fragment.EkoUserProfilePageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_news_feed_home.*

class NewsFeedHomeActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed_home)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = getNewsFeed()
        addFragment(fragment)

        val appLinkUri = intent.extras?.get("uri")
        //TODO plus appLinkUri with postId from shareable External
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
        return EkoNewsFeedFragment.Builder().build(this)
    }

    private fun getProfile(): Fragment {
        return EkoUserProfilePageFragment.Builder().userId(EkoClient.getUserId()).build(this)
    }

    private fun getExplore(): Fragment {
        return EkoExploreFragment.Builder().build(this)
    }

    private fun getTimeline(): Fragment {
        return EkoFeedFragment.Builder().mine().build(this)
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