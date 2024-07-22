package com.bendix.novel.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseFragment: Fragment() {

    var TAG: String = lazy { this.toString() }.value

    private var mFragmentList: MutableList<Fragment> = mutableListOf()
    // 生命周期相关
    private val fragmentLifecycleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            Log.i(TAG, "fragmentLifecycleCallback onFragmentAttached,$fm,$f")
        }

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            Log.i(TAG, "fragmentLifecycleCallback onFragmentCreated,$fm,$f")
            mFragmentList.add(f)
        }

        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            Log.i(TAG, "fragmentLifecycleCallback onFragmentViewCreated,$fm,$f")
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            Log.i(TAG, "fragmentLifecycleCallback onFragmentDestroyed,$fm,$f")
            mFragmentList.remove(f)
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            Log.i(TAG, "fragmentLifecycleCallback onFragmentDetached,$fm,$f")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        childFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallback, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")
        initView()
        initListener()
        initData()
    }

    open fun initView() {}

    /**
     * 初始化监听
     */
    open fun initListener() {}

    /**
     * 初始化数据
     */
    open fun initData() {}


    open fun getLayoutId():Int{
        return 0
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

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

}