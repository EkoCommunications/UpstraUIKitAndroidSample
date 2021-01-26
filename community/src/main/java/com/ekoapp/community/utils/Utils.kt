package com.ekoapp.community.utils

import android.content.Context
import android.content.Intent

fun Context.shareLinkToExternalApp(uri: String) {
    val sendIntent = Intent()
    sendIntent.action = Intent.ACTION_SEND
    sendIntent.putExtra(Intent.EXTRA_TEXT, uri)
    sendIntent.type = "text/plain"
    startActivity(sendIntent)
}