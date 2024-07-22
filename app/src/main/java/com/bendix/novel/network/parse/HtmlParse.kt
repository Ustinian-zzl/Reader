package com.bendix.novel.network.parse

import com.bendix.novel.model.ResponseSearchPageByKeyword
import com.bendix.novel.model.BookBean
import com.bendix.novel.network.HtmlService

open class HtmlParse(protected val htmlService: HtmlService) {

    open val typeMap: Map<String, String> = mapOf()

    open suspend fun getBookInfo(bookUrl: String): BookBean? {
        return null
    }


    open suspend fun postBookInfo(bookUrl: String,body: Map<String,String>): BookBean? {
        return null
    }

    open suspend fun getSearchByKeyword(keyword: String, page: Int): ResponseSearchPageByKeyword {
        return ResponseSearchPageByKeyword()
    }

}