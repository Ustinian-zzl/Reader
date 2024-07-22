package com.bendix.novel.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity(tableName = "my_shelf")
@Parcelize
data class BookBean constructor(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name: String = "",// 书名
    var url: String = "",// 书籍网络地址
    var category: String = "",// 书籍类型
    var cover: String = "",// 封面地址
    var author: String = "",// 作者
    var desc: String = "",// 描述
    var chaptersUrl: String = "",// 目录地址
    var charCount: Int = 0,// 字数
    var chapterCount: Int = 0,// 章节数
) : Parcelable {
    
    @Ignore
    constructor():this(0)
    
}