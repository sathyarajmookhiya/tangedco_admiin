package com.mslabs.tangetco.util

import android.graphics.Color

/**
 * Project           : Smarthome
 * File Name         : ColorUtils
 * Description       :
 * Revision History  : version 1
 * Date              : 2020-05-23
 * Original author   : Kannappan
 * Description       : Initial version
 */
object ColorUtils {

    fun getColorWithAlpha(color: Int, ratio: Float): Int {
        var newColor = 0
        val alpha = Math.round(Color.alpha(color) * ratio).toInt()
        val r: Int = Color.red(color)
        val g: Int = Color.green(color)
        val b: Int = Color.blue(color)
        newColor = Color.argb(alpha, r, g, b)
        return newColor
    }
}