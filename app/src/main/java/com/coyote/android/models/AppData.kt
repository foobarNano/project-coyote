package com.coyote.android.models

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

data class AppData(
    val name: String,
    val category: String,
    val className: String,
    val icon: Drawable,
    val iconBitmapSmall: Bitmap,
    val iconBitmapBig: Bitmap
) {
    override fun toString(): String { return name }
}
