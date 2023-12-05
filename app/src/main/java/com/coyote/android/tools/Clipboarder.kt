package com.coyote.android.tools

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

abstract class AClipboarder(context: Context) {

    protected val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager

    abstract fun readClipboard(): String
    abstract fun postClipboard(label: String, text: String)
}

class Clipboarder(context: Context) : AClipboarder(context) {

    override fun readClipboard(): String {

        return clipboard?.primaryClip?.getItemAt(0)?.text.toString()
    }

    override fun postClipboard(label: String, text: String) {

        val clip = ClipData.newPlainText(label, text)
        clipboard?.setPrimaryClip(clip)
    }
}