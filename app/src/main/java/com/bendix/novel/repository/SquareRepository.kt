package com.bendix.novel.repository

import android.util.Log
import com.bendix.novel.model.ResponseSearchPageByKeyword
import androidx.annotation.WorkerThread
import com.bendix.novel.model.RequestBookInfo
import com.bendix.novel.model.RequestSearchPageByKeyword
import com.bendix.novel.model.ResponseBookInfo
import com.bendix.novel.network.HtmlClient
import com.bendix.novel.persistence.BookDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class SquareRepository @Inject constructor(
 
    private val htmlClient: HtmlClient,
    private val bookDao: BookDao
    
) {

    @WorkerThread
    suspend fun fetchSearchKeyWord(
        request: RequestSearchPageByKeyword,
        onSuccess: (String)->Unit,
        onError: (String)->Unit
    )
    = flow {
        try {
            var keyword = request.keyword
            val page = request.page
            Log.d("SquareRepository.kt",keyword.toString())
            Log.d("SquareRepository.kt",page.toString())

            if (keyword.isBlank()) {
                keyword = ""
            }
            val responseSearch =
                htmlClient.getSearchByKeyword(keyword, page)
            Log.d("SquareRepository.kt",responseSearch.toString())

            emit(responseSearch)
        }catch (e: Exception) {
            onError(e.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun fetchBookInfo(
        request: RequestBookInfo,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow{

        val bookUrl = request.bookUrl
        if (bookUrl.isNotBlank()) {
            // 从数据库中获取书籍
//            val book = bookDao.getByFavoriteAndUrl(bookUrl)
            val book = null
            if (book != null) {
                // 数据库获取成功
//                emit(ResponseBookInfo(book))
                onSuccess("获取书籍信息成功")
            } else {
                //数据库获取失败，从网络获取
                val remoteBook = htmlClient.getBookInfo(bookUrl)
                Log.d("remoteBook",remoteBook.toString())
                if (remoteBook != null) {
                    // 如果获取成功则缓存到数据库
                    val temp = bookDao.getByUrl(remoteBook.url)
                    if (temp != null) {
                        remoteBook.id = temp.id
                    }
                    bookDao.insert(remoteBook)
                    emit(ResponseBookInfo(remoteBook))
                    onSuccess("获取书籍信息成功")
                } else {
                    onError("获取书籍信息失败")
                }
            }
        } else {
            onError("获取失败，书籍链接为空")
        }

    }
}