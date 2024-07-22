package com.bendix.novel.utils

import android.widget.Toast
import com.bendix.novel.ui.base.ContextProvider

object ToastUtils {
    fun showToast(text: String) {
        Toast.makeText(ContextProvider.mContext, text, Toast.LENGTH_SHORT).show()
    }
}