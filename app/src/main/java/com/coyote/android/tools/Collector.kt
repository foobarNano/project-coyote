package com.coyote.android.tools

import android.content.Context
import com.coyote.android.tools.grabbers.getAppListGrabber
import com.coyote.android.tools.grabbers.getHardwareGrabber
import com.coyote.android.tools.grabbers.getSecurityGrabber
import com.coyote.android.tools.grabbers.getSoftwareGrabber

abstract class Collector(context: Context) {
}

fun getCollector(context: Context): Collector {
    return CollectorImpl(context)
}

class CollectorImpl(context: Context) : Collector(context) {

    private val appListGrabber = getAppListGrabber(context)
    private val hardwareGrabber = getHardwareGrabber(context)
    private val softwareGrabber = getSoftwareGrabber(context)
    private val securityGrabber = getSecurityGrabber(context)
}