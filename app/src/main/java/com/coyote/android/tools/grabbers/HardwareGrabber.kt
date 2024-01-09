package com.coyote.android.tools.grabbers

import android.content.Context

abstract class HardwareGrabber(context: Context) {
    // TODO: HardwareGrabber
}

fun getHardwareGrabber(context: Context): HardwareGrabber {

    return HardwareGrabberImpl(context)
}

class HardwareGrabberImpl(context: Context) : HardwareGrabber(context) {
    // TODO: HardwareGrabberImpl
}