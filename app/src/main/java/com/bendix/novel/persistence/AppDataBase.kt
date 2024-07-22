package com.bendix.novel.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bendix.novel.model.BookBean


@Database(entities = [
    BookBean::class, ], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {

    abstract fun bookDao(): BookDao


}