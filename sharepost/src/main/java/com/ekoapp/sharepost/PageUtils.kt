package com.ekoapp.sharepost

import android.content.Context
import android.content.Intent
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import com.ekoapp.ekosdk.uikit.community.newsfeed.activity.EkoPostDetailsActivity
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_COMMUNITY_ID
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_NEWS_FEED_ID
import com.ekoapp.sharepost.MockData.postId

fun Context.openDefaultCommunityHomePage() {
    val intent = Intent(this, EkoCommunityHomePageActivity::class.java)
    startActivity(intent)
}

fun Context.openDefaultPostDetailPage() {
    val intent = Intent(this, EkoPostDetailsActivity::class.java)
    intent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, postId)
    startActivity(intent)
}

fun Context.openPostDetailFromCommunityPage(communityId: String, postId: String) {
    val communityHomeIntent = Intent(this, CommunityHomePageActivity::class.java)
    val communityIntent = Intent(this, CommunityActivity::class.java)
    communityIntent.putExtra(EXTRA_PARAM_COMMUNITY_ID, communityId)
    val postDetailIntent = Intent(this, PostDetailActivity::class.java)
    postDetailIntent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, postId)

    startActivities(arrayOf(communityHomeIntent, communityIntent, postDetailIntent))
}