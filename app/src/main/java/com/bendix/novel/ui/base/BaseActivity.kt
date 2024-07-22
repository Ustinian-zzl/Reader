package com.bendix.novel.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bendix.novel.R
import com.bendix.novel.utils.LogUtil
import com.bendix.novel.utils.StatusBarUtil

abstract class BaseActivity: AppCompatActivity() {

    protected var TAG: String = lazy { this.javaClass.simpleName }.value

    private var mFragmentList: MutableList<Fragment> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setWindowStatusBarColor(this, R.color.main_color)

    }

    open fun getLayoutId():Int{
        return 0
    }

    open fun initListener() {}
    open fun initData() {}



    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}