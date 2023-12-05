package com.coyote.android.tools

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.core.graphics.drawable.toBitmap
import com.coyote.android.models.AppData

abstract class AResourceGrabber(protected val context: Context) {

    protected val iconSize = 100

    protected abstract fun getApplicationList(): List<ApplicationInfo>
    abstract fun getUserApps(): List<AppData>
    abstract fun getSystemApps(): List<AppData>
}

class ResourceGrabber(context: Context) : AResourceGrabber(context) {

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
                iconBitmap = drawable.toBitmap(iconSize, iconSize)
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
                    iconBitmap = drawable.toBitmap(iconSize, iconSize)
                ) }
    }
}