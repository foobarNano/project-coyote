package com.coyote.android.tools.grabbers

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.core.graphics.drawable.toBitmap
import com.coyote.android.models.AppData

interface AppListGrabber {

    fun getApplicationList(): List<ApplicationInfo>
    fun getUserApps(): List<AppData>
    fun getSystemApps(): List<AppData>
}

fun getAppListGrabber(context: Context): AppListGrabber {

    return AppListGrabberImpl(context)
}

class AppListGrabberImpl(private val context: Context) : AppListGrabber {

    private val iconSize = 100

    @SuppressLint("QueryPermissionsNeeded")
    override fun getApplicationList(): List<ApplicationInfo> {

        return context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }

    override fun getUserApps(): List<AppData> {

        return getApplicationList()
            .filter { it.flags and ApplicationInfo.FLAG_SYSTEM != 1 }
            .map {
                val drawable = it.loadIcon(context.packageManager)

                AppData(
                    name = it.packageName,
                    category = if (it.category != -1) it.category.toString() else "Unknown",
                    className = if (it.className.isNullOrEmpty()) "-" else it.className.split('.').last(),
                    icon = drawable,
                    iconBitmapSmall = drawable.toBitmap(iconSize, iconSize),
                    iconBitmapBig = drawable.toBitmap(iconSize*4, iconSize*4)
            ) }
    }

    override fun getSystemApps(): List<AppData> {

        return getApplicationList()
            .filter { it.flags and ApplicationInfo.FLAG_SYSTEM == 1 }
            .map {
                val drawable = it.loadIcon(context.packageManager)

                AppData(
                    name = it.packageName,
                    category = if (it.category != -1) it.category.toString() else "Unknown",
                    className = if (it.className.isNullOrEmpty()) "-" else it.className.split('.').last(),
                    icon = drawable,
                    iconBitmapSmall = drawable.toBitmap(iconSize, iconSize),
                    iconBitmapBig = drawable.toBitmap(iconSize*4, iconSize*4)
                ) }
    }
}