package com.ekoapp.sharepost

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import com.ekoapp.ekosdk.uikit.community.newsfeed.activity.EkoPostDetailsActivity
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_COMMUNITY_ID
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_NEWS_FEED_ID
import com.ekoapp.sharepost.MockData.DEST_NOTIFICATION
import com.ekoapp.sharepost.MockData.EXTRA_DESTINATION
import com.ekoapp.sharepost.MockData.communityId
import com.ekoapp.sharepost.MockData.deepLinkHost
import com.ekoapp.sharepost.MockData.notiChannelId
import com.ekoapp.sharepost.MockData.notiId
import com.ekoapp.sharepost.MockData.notiTitle
import com.ekoapp.sharepost.MockData.postId
import com.ekoapp.sharepost.MockData.postIdFromCommunity
import kotlinx.android.synthetic.main.activity_feature_list.*
import java.util.*
import kotlin.concurrent.schedule

class FeatureListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_list)

        setupView()
        setupEvent()
        handleDeepLink()
    }

    @SuppressLint("SetTextI18n")
    fun setupView() {
        tvOpenPostDetail.text = "$deepLinkHost?postId=${postId}"
        tvOpenPostDetail.setOnClickListener {
            val intent = Intent(this, EkoPostDetailsActivity::class.java)
            intent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, postId)
            startActivity(intent)
        }

        tvOpenCommunityHome.text = "$deepLinkHost?postId=$postIdFromCommunity"
        tvOpenCommunityHome.setOnClickListener {
            val communityHomeIntent = Intent(this, CommunityHomePageActivity::class.java)
            val communityIntent = Intent(this, CommunityActivity::class.java)

            communityIntent.putExtra(EXTRA_PARAM_COMMUNITY_ID, communityId)
            startActivities(arrayOf(communityHomeIntent, communityIntent))

            val postDetailIntent = Intent(this, PostDetailActivity::class.java)
            postDetailIntent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, postId)
            Timer().schedule(500) { startActivity(postDetailIntent) }
        }
    }

    private fun setupEvent() {
        btnOpenDefaultCommunity.setOnClickListener {
            val intent = Intent(this, EkoCommunityHomePageActivity::class.java)
            startActivity(intent)
        }
        btnNotificationPostDetail.setOnClickListener { pushNotification() }
    }

    private fun handleDeepLink() {
        val uri = intent.data
        if (uri != null) {
            val destination = intent.getStringExtra(EXTRA_DESTINATION)
            if (!destination.isNullOrEmpty() && destination == DEST_NOTIFICATION) {
                val communityId = intent.getStringExtra(EXTRA_PARAM_COMMUNITY_ID) ?: ""
                val postId = intent.getStringExtra(EXTRA_PARAM_NEWS_FEED_ID) ?: ""

                val communityHomeIntent = Intent(this, CommunityHomePageActivity::class.java)
                val communityIntent = Intent(this, CommunityActivity::class.java)

                communityIntent.putExtra(EXTRA_PARAM_COMMUNITY_ID, communityId)
                startActivities(arrayOf(communityHomeIntent, communityIntent))

                val postDetailIntent = Intent(this, PostDetailActivity::class.java)
                postDetailIntent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, postId)
                Timer().schedule(500) { startActivity(postDetailIntent) }
            }
        }
    }

    private fun pushNotification() {
        val intent = Intent(this, FeatureListActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            data = Uri.parse(deepLinkHost)
        }
        intent.putExtra(EXTRA_DESTINATION, DEST_NOTIFICATION)
        intent.putExtra(EXTRA_PARAM_NEWS_FEED_ID, postIdFromCommunity)
        intent.putExtra(EXTRA_PARAM_COMMUNITY_ID, communityId)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, notiChannelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(notiTitle)
            .setContentText("postId: $postIdFromCommunity")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val mNotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(notiChannelId, title, NotificationManager.IMPORTANCE_HIGH)
            mNotificationManager.createNotificationChannel(channel)
            builder.setChannelId(notiChannelId)
        }
        mNotificationManager.notify(notiId, builder.build())
    }
}
