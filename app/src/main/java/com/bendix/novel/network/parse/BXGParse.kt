package com.bendix.novel.network.parse

import android.util.Log
import com.bendix.novel.model.ResponseSearchPageByKeyword
import com.bendix.novel.model.BookBean
import com.bendix.novel.model.BookShopInfo
import com.bendix.novel.network.HtmlService
import com.bendix.novel.utils.LogUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class BXGParse(htmlService: HtmlService): HtmlParse(htmlService) {


    private val bookShopInfo = BookShopInfo(
        shopName = "笔仙阁",   //书城名字
        protocol = "https",   //协议
        host = "m.bixiange.me",//书城地址
        bookInfoPath = "/n/",//详情后缀
        bookChapterListPath = "index_%s.html",//目录列表后缀
        bookChapterContentPath = ".html",//具体章节后缀
        bookSearchByKeyPath = "e/search/indexpage.php",//搜索后缀
        bookSearchByTypePath = "wapsort/%s_%s.html"//搜索类型后缀
    )
    
    override suspend fun getBookInfo(bookUrl: String): BookBean? {
        
        return super.getBookInfo(bookUrl)
    }

    override suspend fun postBookInfo(bookUrl: String, body: Map<String, String>): BookBean? {
        
        val html=htmlService.postHtml(bookUrl, mapOf(),body)
        var bookBean: BookBean? = null
        var document:Document?= null
        document = Jsoup.parse(html)
        LogUtil.i("zzl","$document")


        return super.postBookInfo(bookUrl, body)
        
    }

     override suspend fun getSearchByKeyword(keyword: String, page: Int): ResponseSearchPageByKeyword {

         val baseUrl  = "${bookShopInfo.protocol}://${bookShopInfo.host}"

         val url = "${baseUrl}/${bookShopInfo.bookSearchByKeyPath}"

         val body= HashMap<String, String>()
         body.put("keyboard",keyword)
         body.put("show","title")
         Log.d("BXGParse",body.toString())
         val html = htmlService.postHtml(url,mapOf(),body)
         Log.d("BXGParse.kt",html.toString())

         var currentPage = 0
         var totalPage = 0
         val bookModels = ArrayList<BookBean>()
         var document: Document? = null

         document = Jsoup.parse(html)
         Log.d("zzl document",document.toString())

         if (document != null) {
             val list = document.select(".clearfix li")
             Log.d("zzl documentList",list.toString())
             for(it in list){
                 val img=it.select(".cover img")
                 val coverUrl=img.attr("src")
                 Log.d("zzl coverUrl",coverUrl.toString())
                 val a = it.select(".title a")
                 val name = a.html()
                 Log.d("zzl name",name.toString())
                 val bookUrl = baseUrl + a.attr("href")
                 val d = it.select(".descript a")
                 val desc=d.html()
                 Log.d("zzl desc",desc.toString())
                 val bookModel = BookBean()
                 bookModel.cover = baseUrl+coverUrl
                 bookModel.desc = desc
                 bookModel.name=name
                 bookModels.add(bookModel)
             }
         }
             return ResponseSearchPageByKeyword(keyword,0,1,bookModels)
    }
}