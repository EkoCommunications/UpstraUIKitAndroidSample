package com.amity.sharepost

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
import com.amity.socialcloud.uikit.community.utils.EXTRA_PARAM_COMMUNITY_ID
import com.amity.socialcloud.uikit.community.utils.EXTRA_PARAM_NEWS_FEED_ID
import com.amity.sharepost.MockData.DESTINATION
import com.amity.sharepost.MockData.EXTRA_DESTINATION
import com.amity.sharepost.MockData.communityId
import com.amity.sharepost.MockData.deepLinkHost
import com.amity.sharepost.MockData.notiChannelId
import com.amity.sharepost.MockData.notiId
import com.amity.sharepost.MockData.notiTitle
import com.amity.sharepost.MockData.postIdFromCommunity
import com.amity.sharepost.pages.CommunityActivity
import com.amity.sharepost.pages.CommunityHomePageActivity
import com.amity.sharepost.pages.PostDetailActivity
import kotlinx.android.synthetic.main.activity_feature_list.*
import java.util.*
import kotlin.concurrent.schedule

class FeatureListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_list)
        setupView()
        handleDeepLink()
    }

    @SuppressLint("SetTextI18n")
    fun setupView() {

        // ex. launching activities from push notifications
        btnNotificationPostDetail.setOnClickListener {
            createPushNotification()
        }
    }

    private fun handleDeepLink() {
        val shouldHandleDeepLink =
            intent.data != null && intent.getStringExtra(EXTRA_DESTINATION) == DESTINATION
        if (shouldHandleDeepLink) {
            // parse data
            val communityId = intent.getStringExtra(EXTRA_PARAM_COMMUNITY_ID) ?: ""
            val postId = intent.getStringExtra(EXTRA_PARAM_NEWS_FEED_ID) ?: ""

            // create intent
            val communityHomeIntent = Intent(this, CommunityHomePageActivity::class.java)
            val communityIntent = Intent(this, CommunityActivity::class.java).apply {
                putExtra(EXTRA_PARAM_COMMUNITY_ID, communityId)
            }
            val postDetailIntent = Intent(this, PostDetailActivity::class.java).apply {
                putExtra(EXTRA_PARAM_NEWS_FEED_ID, postId)
            }

            // launch intents
            startActivity(communityHomeIntent)
            Timer().schedule(300) { startActivity(communityIntent) }
            Timer().schedule(500) { startActivity(postDetailIntent) }
        }
    }

    private fun createPushNotification() {
        val intent = Intent(this, FeatureListActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            data = Uri.parse(deepLinkHost)
        }
        intent.putExtra(EXTRA_DESTINATION, DESTINATION)
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

        val mNotificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(notiChannelId, title, NotificationManager.IMPORTANCE_HIGH)
            mNotificationManager.createNotificationChannel(channel)
            builder.setChannelId(notiChannelId)
        }
        mNotificationManager.notify(notiId, builder.build())
    }

}
