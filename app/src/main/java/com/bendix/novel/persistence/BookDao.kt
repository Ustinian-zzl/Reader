package com.bendix.novel.persistence

import androidx.room.*
import com.bendix.novel.model.BookBean

@Dao
interface BookDao: BaseBeanDao<BookBean> {
    @Query("SELECT * FROM my_shelf WHERE url = :bookUrl")
    suspend fun getByUrl(bookUrl: String): BookBean?
    @Query("SELECT * FROM my_shelf")
    suspend fun getList(): List<BookBean>
    @Query("SELECT * FROM my_shelf WHERE name IN (:names)")
    suspend fun getListByNames(names: List<String>): List<BookBean>
//    suspend fun getByFavoriteAndUrl(bookUrl: String): BookBean


}