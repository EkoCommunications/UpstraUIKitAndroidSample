package com.ekoapp.chatkitmessagelistwithcustomui

import androidx.databinding.ObservableField
import com.ekoapp.ekosdk.uikit.chat.messages.viewModel.EkoChatMessageBaseViewModel

/**
 * Sample viewModel to be used with [TextSenderViewHolder] and [TextReceiverViewHolder]
 * needs to extend [EkoChatMessageBaseViewModel]
 */
class MyTextMsgViewModel: EkoChatMessageBaseViewModel() {

    val text = ObservableField("")
}