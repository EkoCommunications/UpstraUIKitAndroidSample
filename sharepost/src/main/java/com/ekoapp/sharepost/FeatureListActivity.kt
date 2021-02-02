package com.ekoapp.sharepost

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import com.ekoapp.ekosdk.uikit.community.newsfeed.activity.EkoPostDetailsActivity
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

        handleDeepLink(this::openPostDetailPage)
    }

    @SuppressLint("SetTextI18n")
    private fun openPostDetailPage(it: Uri) {
        val fakePostId = "ad737dd23f0b5d30853438a2ae42777a"
        tvOpenPostDetail.text = "$it?id=$fakePostId"
        tvOpenPostDetail.setOnClickListener {
            val intent = Intent(this, EkoPostDetailsActivity::class.java)
            intent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, fakePostId)
            startActivity(intent)
        }
    }

    private fun handleDeepLink(callback: (Uri) -> Unit) {
        val uri = intent.data
        if (uri != null) callback.invoke(uri)
    }
}
