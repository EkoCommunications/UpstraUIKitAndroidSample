package com.ekoapp.sharepost

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import com.ekoapp.ekosdk.uikit.community.newsfeed.activity.EkoPostDetailsActivity
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_COMMUNITY_ID
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_NEWS_FEED_ID
import kotlinx.android.synthetic.main.activity_feature_list.*
import java.util.*
import kotlin.concurrent.schedule

class FeatureListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_list)

        btnOpenDefaultCommunity.setOnClickListener {
            val intent = Intent(this, EkoCommunityHomePageActivity::class.java)
            startActivity(intent)
        }

        handleDeepLink {
            openPostDetailPage(it)
            openCommunityHomePage(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openPostDetailPage(it: Uri) {
        val fakePostId = "ad737dd23f0b5d30853438a2ae42777a"
        tvOpenPostDetail.text = "$it?postId=$fakePostId"
        tvOpenPostDetail.setOnClickListener {
            val intent = Intent(this, EkoPostDetailsActivity::class.java)
            intent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, fakePostId)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openCommunityHomePage(it: Uri) {
        val fakeCommunityId = "e45f78c3911fddbf5756baa40c97d143"
        val fakePostId = "a266376e59b67f40b2abc0795de53b04"

        tvOpenCommunityHome.text = "$it?postId=$fakePostId"
        tvOpenCommunityHome.setOnClickListener {
            val communityHomeIntent = Intent(this, CommunityHomePageActivity::class.java)
            val communityIntent = Intent(this, CommunityActivity::class.java)
            communityIntent.putExtra(EXTRA_PARAM_COMMUNITY_ID, fakeCommunityId)
            startActivities(arrayOf(communityHomeIntent, communityIntent))

            val postDetailIntent = Intent(this, PostDetailActivity::class.java)
            postDetailIntent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, fakePostId)
            Timer().schedule(500) { startActivity(postDetailIntent) }
        }
    }

    private fun handleDeepLink(callback: (Uri) -> Unit) {
        val uri = intent.data
        if (uri != null) callback.invoke(uri)
    }
}
