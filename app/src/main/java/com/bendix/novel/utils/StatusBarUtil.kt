package com.bendix.novel.utils

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager
import com.bendix.novel.ui.base.BaseActivity

object StatusBarUtil {


    fun setWindowStatusBarColor(activity: Activity, colorResId: Int) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window: Window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = activity.resources.getColor(colorResId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}