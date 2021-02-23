package com.ekoapp.sharepost

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import com.ekoapp.ekosdk.uikit.community.newsfeed.activity.EkoPostDetailsActivity
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_COMMUNITY_ID
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_NEWS_FEED_ID
import kotlinx.android.synthetic.main.activity_feature_list.*

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
            val postDetailIntent = Intent(this, PostDetailActivity::class.java)
            postDetailIntent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, fakePostId)

            val sBuilder = TaskStackBuilder.create(this)
                .addNextIntent(communityHomeIntent)
                .addNextIntent(communityIntent)
                .addNextIntent(postDetailIntent)

            val pendingIntent = sBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            val intentSender = pendingIntent?.intentSender
            startIntentSender(intentSender, postDetailIntent, 0, 0, 0)
        }
    }

    private fun handleDeepLink(callback: (Uri) -> Unit) {
        val uri = intent.data
        if (uri != null) callback.invoke(uri)
    }
}
