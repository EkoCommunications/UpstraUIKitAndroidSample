package com.ekoapp.sharepost

import android.app.Application
import android.content.Context
import android.content.Intent
import com.amity.socialcloud.sdk.AmityCoreClient
import com.amity.socialcloud.sdk.social.feed.AmityPost
import com.amity.socialcloud.uikit.AmityUIKitClient
import com.amity.socialcloud.uikit.feed.settings.AmityPostShareClickListener
import com.amity.socialcloud.uikit.feed.settings.AmityPostSharingSettings
import com.amity.socialcloud.uikit.feed.settings.AmityPostSharingTarget

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val apiKey = getString(R.string.upstra_api_key)

        AmityCoreClient.setup(apiKey)

        val settings = AmityPostSharingSettings()
        settings.publicCommunityPostSharingTarget = listOf(AmityPostSharingTarget.External)
        AmityUIKitClient.socialUISettings.postSharingSettings = settings

        AmityUIKitClient.socialUISettings.postShareClickListener = object :
            AmityPostShareClickListener {
            override fun shareToExternal(context: Context, post: AmityPost) {
                // Create intent for link sharing
                val fakeURL = "https://www.upstra.co/postDetail?id=" + post.getPostId()
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, fakeURL)
                context.startActivity(Intent.createChooser(shareIntent, "Share link using"))
            }
        }
    }
}