package com.bendix.novel.ui.base

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.bendix.novel.utils.SpUtil

class ContextProvider: ContentProvider() {


    companion object {
        lateinit var mContext: Context
    }
    override fun onCreate(): Boolean {
        mContext = context!!
        SpUtil.init(context!!)
        return false

    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        
        return null

    }

    override fun getType(uri: Uri): String? {
        return null    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0   
    }
}