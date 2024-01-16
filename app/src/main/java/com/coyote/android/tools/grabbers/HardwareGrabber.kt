package com.coyote.android.tools.grabbers

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.os.StatFs
import java.io.File

interface HardwareGrabber {

    fun getBrand(): String
    fun getModel(): String
    fun getChipset(): String
    fun getMemory(): Long
    fun getAvailableStorage(): Long
    fun getTotalStorage(): Long
    fun getBattery(): Long
    fun getSensors(): List<SensorData>
}

data class SensorData(
    val name: String,
    val provider: String
) {
    private val repr = StringBuilder(name).append(" (").append(provider).append(')').toString()
    override fun toString(): String {
        return repr
    }
}

fun getHardwareGrabber(context: Context): HardwareGrabber {

    return HardwareGrabberImpl(context)
}

class HardwareGrabberImpl(context: Context) : HardwareGrabber {
    private val sensorManager = context.getSystemService(Service.SENSOR_SERVICE) as SensorManager

    override fun getBrand(): String {
        return Build.BRAND!!
    }

    override fun getModel(): String {
        return Build.MODEL!!
    }

    override fun getChipset(): String {
        // TODO: Reformat this stuff
        return File("/proc/cpuinfo").readText()
    }

    override fun getMemory(): Long {
        return ActivityManager.MemoryInfo().totalMem / 1048576 // in MB
    }

    override fun getAvailableStorage(): Long {
        val stat = StatFs(Environment.getExternalStorageDirectory().path)
        return stat.availableBytes / 1048576 // in MB
    }

    override fun getTotalStorage(): Long {
        val stat = StatFs(Environment.getExternalStorageDirectory().path)
        return stat.totalBytes / 1048576 // in MB
    }

    override fun getBattery(): Long {
        val charge = BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER
        val capacity = BatteryManager.BATTERY_PROPERTY_CAPACITY
        return (charge / capacity * 100).toLong()
    }

    override fun getSensors(): List<SensorData> {
        return sensorManager.getSensorList(Sensor.TYPE_ALL).map { SensorData(it.name, it.vendor) }
    }

}