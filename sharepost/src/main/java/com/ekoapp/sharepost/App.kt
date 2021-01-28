package com.ekoapp.sharepost

import android.app.Application
import android.util.Log
import com.ekoapp.ekosdk.EkoClient
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
    }
}