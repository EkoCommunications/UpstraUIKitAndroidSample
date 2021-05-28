package com.amity.sharepost

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.sdk.AmityCoreClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (shouldAutoSignIn()) {
            navigateToFeatureList()
        } else {
            initView()
        }

    }

    private fun initView() {
        btnLogin.setOnClickListener {
            if (etUserId.text.isNotEmpty() && etUserName.text.isNotEmpty()) {
                signInUser(etUserId.text.toString(), etUserName.text.toString())
            } else {
                Toast.makeText(this, "Enter userId and Display Name", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signInUser(userId: String, displayName: String) {
        AmityCoreClient.registerDevice(userId)
            .displayName(displayName)
            .build()
            .submit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                navigateToFeatureList()
            }
            .doOnError {
                Toast.makeText(this, "Could not register user " + it.message, Toast.LENGTH_LONG)
                    .show()
            }
            .subscribe()
    }

    private fun navigateToFeatureList() {
        val intent = Intent(this, FeatureListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun shouldAutoSignIn(): Boolean {
        return AmityCoreClient.getUserId().isNotEmpty()
    }

}