package com.ekoapp.sharepost.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekoapp.ekosdk.uikit.community.newsfeed.fragment.EkoPostDetailFragment
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_NEWS_FEED_ID
import com.ekoapp.sharepost.R

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val postId = intent.getStringExtra(EXTRA_PARAM_NEWS_FEED_ID) ?: ""
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getPostDetailFragment(postId)
            fragmentTransaction.replace(com.ekoapp.ekosdk.uikit.R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }
    }

    private fun getPostDetailFragment(postId: String): Fragment {
        return EkoPostDetailFragment.Builder()
            .postId(postId)
            .build(this)
    }

}