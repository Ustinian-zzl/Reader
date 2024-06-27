package com.bendix.novel

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URLEncoder


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bendix.novel", appContext.packageName)
    }

    @Test
    fun test(){

        Log.d("zzl","单元测试开始")

//        // 构建Retrofit实例
//        val retrofit = Retrofit.Builder() //设置网络请求BaseUrl地址
//            .baseUrl("https://www.baidu.com/") //设置数据解析器
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
        val client = OkHttpClient().newBuilder().build()


        val formBody: FormBody = FormBody.Builder()
            .addEncoded("keyboard", URLEncoder.encode("斗罗","gb2312")) // 添加已编码的键值对
            .add("show", "title")
            .build()
        val request: Request = Request.Builder()
            .url("https://m.bixiange.me/e/search/indexpage.php")
            .post(formBody)
            .build()
        val response: Response = client.newCall(request).execute()


        if (response.isSuccessful) {
            val responseData = response.body!!.string()
            Log.d("返回值",responseData);
            // 处理成功响应的数据
        } else {
            // 处理请求失败的情况
        }


        Log.d("zzl","单元测试结束")

    }

}