package com.ekoapp.chatkitmessagelistwithcustomui

import androidx.databinding.ObservableField
import com.amity.socialcloud.uikit.chat.messages.viewModel.AmityChatMessageBaseViewModel

/**
 * Sample viewModel to be used with [TextSenderViewHolder] and [TextReceiverViewHolder]
 * needs to extend [AmityChatMessageBaseViewModel]
 */
class MyTextMsgViewModel : AmityChatMessageBaseViewModel() {

    val text = ObservableField("")
}