package com.bendix.novel.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bendix.novel.R
import com.bendix.novel.databinding.ActivityMainBinding
import com.bendix.novel.databinding.FragmentSquareBinding
import com.bendix.novel.ui.base.BaseActivity
import com.bendix.novel.utils.LogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: ActivityMainBinding? =null// 声明绑定类变量
    private var viewPager: ViewPager2? =null// 声明绑定类变量

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        viewPager=binding!!.viewPager
        Log.d("zzl","MainActivity initView")
        binding!!.viewPager.adapter =
            MainAdapter(this@MainActivity)
        initListener()
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
    
    

    override fun initListener() {
        // 设置切换标签事件
       binding!!.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_square -> {
                    if (viewPager!!.currentItem != 0) {
                        viewPager!!.currentItem = 0
                    }
                    Log.d("zzl","MainActivity initListener")

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_shelf -> {
                    if (viewPager!!.currentItem != 1) {
                        viewPager!!.currentItem = 1
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_me -> {
                    if (viewPager!!.currentItem != 2) {
                        viewPager!!.currentItem = 2
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
        
    }
    
    
    
    
}