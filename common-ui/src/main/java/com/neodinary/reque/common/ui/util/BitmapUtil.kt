package com.neodinary.reque.common.ui.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

object BitmapUtil {
    fun bitmapToString(bitmap: Bitmap): String? {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream)
        val bitmapByteArr = stream.toByteArray()
        return Base64.encodeToString(bitmapByteArr, Base64.DEFAULT)
    }

    fun stringToBitmap(bitmapString: String): Bitmap? {
        try {
            val encodeByte = Base64.decode(bitmapString, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            return bitmap
        } catch (e: Exception) {
            return null
        }
    }
}