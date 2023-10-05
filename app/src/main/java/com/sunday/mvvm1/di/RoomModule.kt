package com.sunday.mvvm1.di

import android.content.Context
import androidx.room.Room
import com.sunday.mvvm1.data.database.QuoteDatabase
import com.sunday.mvvm1.data.database.dao.QuoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "quote_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): QuoteDatabase {
        return Room
            .databaseBuilder(context, QuoteDatabase::class.java, QUOTE_DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteDao(db: QuoteDatabase): QuoteDao {
        return db.getQuoteDao()
    }

}