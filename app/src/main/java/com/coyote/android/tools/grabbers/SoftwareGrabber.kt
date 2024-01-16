package com.coyote.android.tools.grabbers

import android.content.Context
import android.icu.util.TimeZone
import android.os.Build
import android.provider.Settings

interface SoftwareGrabber {
    fun getAndroidVersion(): String
    fun getSdkVersion(): Int
    fun getRegion(): String
    fun getLanguage(): String
    fun getTime12h(): Boolean
    fun getTimeZone(): String
    fun getDateFormat(): String
}

fun getSoftwareGrabber(context: Context): SoftwareGrabber {

    return SoftwareGrabberImpl(context)
}

class SoftwareGrabberImpl(private val context: Context) : SoftwareGrabber {
    override fun getAndroidVersion(): String {
        return Build.VERSION.RELEASE!!
    }

    override fun getSdkVersion(): Int {
        return Build.VERSION.SDK_INT
    }

    override fun getRegion(): String {
        return context.resources!!.configuration!!.locales.get(0).country
    }

    override fun getLanguage(): String {
        return context.resources!!.configuration!!.locales.get(0).language
    }

    @Suppress("KotlinConstantConditions")
    override fun getTime12h(): Boolean {
        return Settings.System.TIME_12_24 == "12"
    }

    override fun getTimeZone(): String {
        return TimeZone.getDefault()!!.displayName!!
    }

    @Suppress("Deprecation")
    override fun getDateFormat(): String {
        return Settings.System.DATE_FORMAT
    }
}