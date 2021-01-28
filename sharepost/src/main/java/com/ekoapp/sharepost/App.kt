package com.ekoapp.sharepost

import android.app.Application
import android.content.Context
import android.util.Log
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.feed.EkoPost
import com.ekoapp.ekosdk.uikit.community.newsfeed.listener.ISharePostClickListener
import com.ekoapp.ekosdk.uikit.community.views.EkoUIKitClient
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

        EkoUIKitClient.feedUISettings.feedEventHandler = object : ISharePostClickListener{
            override fun shareToExternal(context: Context, post: EkoPost) {
                context.shareLinkToExternalApp(post.getPostId())
            }

        }
    }
}