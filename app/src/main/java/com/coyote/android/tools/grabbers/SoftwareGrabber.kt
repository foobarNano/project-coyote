package com.coyote.android.tools.grabbers

import android.content.Context

abstract class SoftwareGrabber(context: Context) {
    // TODO: SoftwareGrabber
}

fun getSoftwareGrabber(context: Context): SoftwareGrabber {

    return SoftwareGrabberImpl(context)
}

class SoftwareGrabberImpl(context: Context) : SoftwareGrabber(context) {
    // TODO: SoftwareGrabberImpl
}