package com.sunday.mvvm1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sunday.mvvm1.data.database.dao.QuoteDao
import com.sunday.mvvm1.data.database.entity.QuoteEntity

@Database(
    entities = [QuoteEntity::class],
    version = 1
)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
}