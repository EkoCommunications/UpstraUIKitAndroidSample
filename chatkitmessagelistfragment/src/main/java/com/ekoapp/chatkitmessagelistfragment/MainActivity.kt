package com.ekoapp.chatkitmessagelistfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ekoapp.ekosdk.EkoChannel
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.messages.fragment.EkoMessageListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Replace with actual userId[String] and displayName[String]
         */
        EkoClient.registerDevice("testUser2", "Test User2").subscribe()

        btnLoadFragment.setOnClickListener {
            loadFragment()
            btnLoadFragment.visibility = View.GONE
        }
    }

    private fun loadFragment() {
        /**
         * Use fragment builder to create instance of EkoMessageListFragment
         * Replace channelId[String] with actual channelId
         */
        val builder = EkoMessageListFragment.Builder("channelId")
        val messageListFragment = builder.build()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, messageListFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
