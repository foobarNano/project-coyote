package com.coyote.android.tools.grabbers

import android.content.Context

abstract class SecurityGrabber(context: Context) {
    // TODO: SecurityGrabber
}

fun getSecurityGrabber(context: Context): SecurityGrabber {

    return SecurityGrabberImpl(context)
}

class SecurityGrabberImpl(context: Context) : SecurityGrabber(context) {
    // TODO: SecurityGrabberImpl
}