package com.ekoapp.sharepost

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.feed.EkoPost
import com.ekoapp.ekosdk.uikit.EkoUIKitClient
import com.ekoapp.ekosdk.uikit.feed.settings.EkoPostSharingSettings
import com.ekoapp.ekosdk.uikit.feed.settings.EkoPostSharingTarget
import com.ekoapp.ekosdk.uikit.feed.settings.IPostShareClickListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val apiKey = getString(R.string.upstra_api_key)

        EkoClient.setup(apiKey)

        val settings = EkoPostSharingSettings()
        settings.publicCommunityPostSharingTarget = listOf(EkoPostSharingTarget.External)
        EkoUIKitClient.feedUISettings.postSharingSettings = settings

        EkoUIKitClient.feedUISettings.postShareClickListener = object : IPostShareClickListener {
            override fun shareToExternal(context: Context, post: EkoPost) {
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