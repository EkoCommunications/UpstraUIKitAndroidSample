package com.ekoapp.sharepost

import android.app.Application
import android.content.Context
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                Log.d("", "setup success")
            }
            .doOnError {
                it.message?.let { it1 -> Log.d("", it1) }
            }

        //TODO This allow setting share external for example
        val settings = EkoPostSharingSettings()
        settings.myFeedPostSharingTarget = enumValues<EkoPostSharingTarget>().toList()
        EkoUIKitClient.feedUISettings.postSharingSettings = settings

        EkoUIKitClient.feedUISettings.postShareClickListener = object : IPostShareClickListener {
            override fun shareToExternal(context: Context, post: EkoPost) {
                val fakeURL = "https://www.upstra.co/postDetail?id=" + post.getPostId()
                context.shareLinkToExternalApp(fakeURL)
            }
        }
    }
}