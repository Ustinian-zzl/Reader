package com.bendix.novel.persistence

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseBeanDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(element: T)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSome(vararg elements:T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<T>)

    @Update
    suspend fun update(element: T)

    @Update
    suspend fun updateSome(vararg elements:T)

    @Update
    suspend fun updateList(elements:List<T>)

    @Delete
    suspend fun delete(element: T)

    @Delete
    suspend fun deleteSome(vararg elements:T)

    @Delete
    suspend fun deleteList(elements:List<T>)
    
    
    
}