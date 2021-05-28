package com.amity.sharepost.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.amity.sharepost.R
import com.amity.socialcloud.uikit.community.newsfeed.fragment.AmityPostDetailFragment
import com.amity.socialcloud.uikit.community.utils.EXTRA_PARAM_NEWS_FEED_ID

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val postId = intent.getStringExtra(EXTRA_PARAM_NEWS_FEED_ID) ?: ""
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getPostDetailFragment(postId)
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }
    }

    private fun getPostDetailFragment(postId: String): Fragment {
        return AmityPostDetailFragment.newInstance(postId)
            .build(this)
    }

}