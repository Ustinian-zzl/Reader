package com.bendix.novel.network

import com.bendix.novel.model.ResponseSearchPageByKeyword
import com.bendix.novel.model.BookBean
import com.bendix.novel.network.parse.BXGParse
import com.bendix.novel.network.parse.HtmlParse
import javax.inject.Inject

class HtmlClient @Inject constructor(htmlService: HtmlService) {
    
    
    
    private val parseMap:Map<String,HtmlParse> = mapOf("笔仙阁" to BXGParse(htmlService))

    /**
     * 获取网站
     */
    fun getParseArray():List<String>{
        return parseMap.keys.toList()
    }

    /**
     * 获取类型
     */
    fun getTypeArray(shopName:String):List<String>{
        return parseMap[shopName]?.typeMap?.keys!!.toList()
    }

    suspend fun getBookInfo(bookUrl: String): BookBean? {
        val parse = parseMap["笔仙阁"]
        return parse?.getBookInfo(bookUrl)
    }

    suspend fun getSearchByKeyword(keyword: String, page: Int): ResponseSearchPageByKeyword {
        val parse = parseMap["笔仙阁"]
        if(parse!=null){
            return parse.getSearchByKeyword(keyword,page)
        }
        return ResponseSearchPageByKeyword()

    }


}