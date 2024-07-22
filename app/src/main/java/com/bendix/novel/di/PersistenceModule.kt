package com.bendix.novel.di

import android.app.Application
import androidx.room.Room
import com.bendix.novel.persistence.AppDataBase
import com.bendix.novel.persistence.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {


    @Provides
    @Singleton
    fun provideBookDao(appDataBase: AppDataBase): BookDao {
        return appDataBase.bookDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        //typeResponseConverter: TypeResponseConverter
    ): AppDataBase {
        return Room
            .databaseBuilder(application, AppDataBase::class.java, "db_novel.db")
            .fallbackToDestructiveMigration()
            //.addTypeConverter(typeResponseConverter)
            .build()
    }
    
    
}