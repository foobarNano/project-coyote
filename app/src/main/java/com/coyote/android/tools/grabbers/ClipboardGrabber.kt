package com.coyote.android.tools.grabbers

import android.content.Context

abstract class ClipboardGrabber(context: Context) {
    // TODO: ClipboardGrabber
}

fun getClipboardGrabber(context: Context): ClipboardGrabber {

    return ClipboardGrabberImpl(context)
}

class ClipboardGrabberImpl(context: Context) : ClipboardGrabber(context) {
    // TODO: ClipboardGrabberImpl
}