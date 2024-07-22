package com.bendix.novel.ui

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bendix.novel.ui.square.SquareFragment

class MainAdapter(fragmentActivity:FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<Fragment> = SparseArray(3)

    // 初始化
    init {
        fragments.append(0, SquareFragment())
//        fragments.append(1, ShelfFragment())
//        fragments.append(2, MeFragment())
    }
    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }


}