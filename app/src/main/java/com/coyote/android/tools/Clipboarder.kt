package com.coyote.android.tools

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

interface Clipboarder {

    fun readClipboard(): String
    fun postClipboard(label: String, text: String)
}

fun getClipborder(context: Context): Clipboarder {
    return ClipboarderImpl(context)
}

class ClipboarderImpl(context: Context) : Clipboarder {

    private val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager

    override fun readClipboard(): String {

        return clipboard?.primaryClip?.getItemAt(0)?.text.toString()
    }

    override fun postClipboard(label: String, text: String) {

        val clip = ClipData.newPlainText(label, text)
        clipboard?.setPrimaryClip(clip)
    }
}