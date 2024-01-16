package com.coyote.android.tools.grabbers

import android.app.KeyguardManager
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.provider.Settings
import java.security.Security

interface SecurityGrabber {

    fun getAdbEnabled(): Boolean
    fun getDevEnabled(): Boolean
    fun getEncryption(): Int
    fun getPinEnabled(): Boolean
    fun getSecurityProviders(): List<String>
    fun getProxy(): String
    fun getTransitionAniScale(): Int
    fun getWindowAniScale(): Int
    fun getAniDurationScale(): Int
}

fun getSecurityGrabber(context: Context): SecurityGrabber {

    return SecurityGrabberImpl(context)
}

class SecurityGrabberImpl(private val context: Context) : SecurityGrabber {

    private val policyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE)!! as DevicePolicyManager
    private val keyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE)!! as KeyguardManager

    override fun getAdbEnabled(): Boolean {
        return Settings.Secure.getInt(context.contentResolver,
            Settings.Global.ADB_ENABLED, 0) != 0
    }

    override fun getDevEnabled(): Boolean {
        return Settings.Secure.getInt(context.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
    }

    override fun getEncryption(): Int {
        return policyManager.storageEncryptionStatus
    }

    override fun getPinEnabled(): Boolean {
        return keyguardManager.isKeyguardSecure
    }

    override fun getSecurityProviders(): List<String> {
        return Security.getProviders().map { it!!.name }
    }

    override fun getProxy(): String {
        return Settings.Secure.getString(context.contentResolver,
            Settings.Global.HTTP_PROXY)
    }

    override fun getTransitionAniScale(): Int {
        return (Settings.Secure.getFloat(context.contentResolver,
            Settings.Global.TRANSITION_ANIMATION_SCALE, 1.0f) * 100).toInt()
    }

    override fun getWindowAniScale(): Int {
        return (Settings.Secure.getFloat(context.contentResolver,
            Settings.Global.WINDOW_ANIMATION_SCALE, 1.0f) * 100).toInt()
    }

    override fun getAniDurationScale(): Int {
        return (Settings.Secure.getFloat(context.contentResolver,
            Settings.Global.ANIMATOR_DURATION_SCALE, 1.0f) * 100).toInt()
    }

}