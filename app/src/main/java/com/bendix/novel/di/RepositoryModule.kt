package com.bendix.novel.di

import com.bendix.novel.network.HtmlClient
import com.bendix.novel.persistence.BookDao
import com.bendix.novel.repository.SquareRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideSquareRepository(
        htmlClient: HtmlClient,
        bookDao: BookDao,
    ): SquareRepository {
        return SquareRepository(htmlClient,bookDao)
    }
    
    
    
}