package com.ekoapp.chatkitmessagelistactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.sdk.AmityCoreClient
import com.amity.socialcloud.uikit.chat.messages.AmityMessageListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        AmityCoreClient.registerDevice("testUser2").displayName("Test User2").build().submit()
            .subscribe()
        loadMessageList()
    }

    private fun loadMessageList() {
        btnMessageList.setOnClickListener {
            /**
             * Use [AmityMessageListActivity.newIntent] to create AmityMessageList intent
             * Replace channelId[String] with actual value
             */
            val messageIntent = AmityMessageListActivity.newIntent(this, "channelId")
            startActivity(messageIntent)
        }
    }
}
