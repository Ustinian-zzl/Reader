package com.bendix.novel.model

import com.bendix.novel.model.BookBean

data class RequestSearchPageByKeyword(val keyword:String="", val page:Int=1)

data class RequestSearchPageByType(val shopName:String,val typeName:String="", val page:Int=1)

data class RequestBookInfo(val bookUrl: String)

data class RequestAddSign(val mBookUrl: String, val chapterUrl: String, val chapterName: String)

data class RequestChapter(val mCollBook: BookBean, val start: Int, val limit:Int=100, val cacheContents:Boolean=false)