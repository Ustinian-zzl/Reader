package com.bendix.novel.network

import android.util.Log
import android.widget.Toast
import com.bendix.novel.constant.Constant.UserAgent
import com.bendix.novel.utils.LogUtil
import com.bendix.novel.utils.ToastUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder

class HtmlService {

    private fun get(url: String,head: Map<String,String>): String? {
        val client = OkHttpClient()
        val requestBuilder = Request.Builder()
            .removeHeader("User-Agent")
            .addHeader("User-Agent", UserAgent)
        for (hd in head){
            requestBuilder.addHeader(hd.key, hd.value)
        }
        val request = requestBuilder.url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }


    private fun post(url: String,head: Map<String,String>,body: Map<String,String>): String? {
        val client = OkHttpClient()
        val requestBuilder = Request.Builder()
            .removeHeader("User-Agent")
            .addHeader("User-Agent", UserAgent)
        for (hd in head){
            requestBuilder.addHeader(hd.key, hd.value)
        }
        val formBodyBuilder = FormBody.Builder()
//        for (bd in body){
//            if(bd.key=="keyboard"){
//                Log.d("zzl keyboard",bd.value)
//                formBodyBuilder.addEncoded(bd.key,URLEncoder.encode(bd.value,"gb2312"))
//            }
//            formBodyBuilder.add(bd.key, bd.value)
//        }
//        
        val formBody= FormBody.Builder()
            if(body["keyboard"]!=null){
                formBody.addEncoded("keyboard", URLEncoder.encode(body["keyboard"],"gb2312"))
                formBody.add("show", "title")
            }
        val request = requestBuilder.url(url)
            .post(formBody.build())
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }

    suspend fun getHtml(url: String, head: Map<String,String>): String? {
        try {
            return withContext(Dispatchers.IO) { get(url,head) }
        } catch (e: java.io.IOException) {
            LogUtil.e(e.toString())
            ToastUtils.showToast(e.toString())
        }
        return null
    }


    suspend fun postHtml(url: String,head: Map<String,String>,body: Map<String,String>): String? {
        try {
            return withContext(Dispatchers.IO) { post(url,head,body) }
        } catch (e: java.io.IOException) {
            LogUtil.e(e.toString())
            ToastUtils.showToast(e.toString())
        }
        return null
    }
    
}