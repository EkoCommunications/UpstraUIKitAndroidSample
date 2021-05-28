package com.amity.chatkitmessagelistwithcustomui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amity.socialcloud.uikit.chat.messages.adapter.AmityMessageListAdapter
import com.amity.socialcloud.uikit.chat.messages.fragment.AmityMessageListFragment
import com.amity.socialcloud.uikit.chat.messages.viewHolder.AmityChatMessageBaseViewHolder
import com.amity.socialcloud.uikit.chat.util.MessageType
import com.ekoapp.chatkitmessagelistwithcustomui.R

class MessageListActivity : AppCompatActivity(), AmityMessageListAdapter.CustomViewHolderListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)

        val channelId = intent.getStringExtra("CHANNEL_ID") ?: ""

        /**
         * Use Fragment builder to create instance
         * @param [channelId] String pass the channelId
         */
        val messageListFragment = AmityMessageListFragment.newInstance(channelId)
            .build()
        /**
         * Implement [AmityMessageListAdapter.CustomViewHolderListener] if customization is required for messageViews
         * set the customView listener using [AmityMessageListFragment] instance
         */
        messageListFragment.addCustomView(this)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, messageListFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * Use below overriden method to pass the custom  viewHolders based on viewType [MessageType]
     */
    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): AmityChatMessageBaseViewHolder? {
        return when (viewType) {
            MessageType.MESSAGE_ID_TEXT_RECEIVER -> TextReceiverViewHolder(
                inflater.inflate(R.layout.item_text_receiver, parent, false), MyTextMsgViewModel()
            )
            MessageType.MESSAGE_ID_TEXT_SENDER -> TextSenderViewHolder(
                inflater.inflate(R.layout.item_text_sender, parent, false), MyTextMsgViewModel()
            )
            /**
             * For types no customisation is  required pass null
             * Default UIKIT Ui will be rendered for all those types
             */
            else -> null
        }
    }
}