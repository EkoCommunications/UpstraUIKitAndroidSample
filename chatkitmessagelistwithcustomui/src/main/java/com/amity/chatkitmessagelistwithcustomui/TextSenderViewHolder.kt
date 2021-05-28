package com.amity.chatkitmessagelistwithcustomui

import android.view.View
import androidx.databinding.DataBindingUtil
import com.amity.socialcloud.sdk.chat.message.AmityMessage
import com.amity.socialcloud.uikit.chat.messages.viewHolder.AmityChatMessageBaseViewHolder
import com.amity.socialcloud.uikit.chat.messages.viewModel.AmityChatMessageBaseViewModel
import com.ekoapp.chatkitmessagelistwithcustomui.databinding.ItemTextSenderBinding

/**
 * Sample ViewHolder for custom text sender message
 * @param [itemView] View
 * @param [itemViewModel] [AmityChatMessageBaseViewModel]
 * needs to extend [AmityChatMessageBaseViewModel]
 */
class TextSenderViewHolder(
    itemView: View,
    private val itemViewModel: MyTextMsgViewModel
) : AmityChatMessageBaseViewHolder(itemView, itemViewModel) {

    private val binding: ItemTextSenderBinding? = DataBindingUtil.bind(itemView)

    init {
        binding?.viewModel = itemViewModel
    }

    /**
     * Overriden method to set the view
     * @param [message] EkoMessage
     */
    override fun setMessage(message: AmityMessage) {
        val data = message.getData() as AmityMessage.Data.TEXT
        val text = data.getText()
        /**
         * Data binding can be used to set the views
         */
        itemViewModel.text.set(text)

        /**
         * Alternatively individual views can be set
         */
        binding?.tvMessage?.text = text
        binding?.tvTime?.text = itemViewModel.msgTime.get()
    }
}