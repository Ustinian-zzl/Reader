package com.bendix.novel.ui.novelRead

import android.app.Activity
import android.content.Intent
import com.bendix.novel.model.BookBean
import com.bendix.novel.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NovelReadActivity: BaseActivity() {
    
    companion object{
        
        const val EXTRA_COLL_BOOK = "extra_coll_book"
        fun startFromActivity(activity: Activity,bookBean: BookBean){

            val intent = Intent(activity, NovelReadActivity::class.java)
            intent.putExtra(EXTRA_COLL_BOOK, bookBean)
            activity.startActivity(intent)

        }
        
        
    }
    
    
    
    
    
    
    
}