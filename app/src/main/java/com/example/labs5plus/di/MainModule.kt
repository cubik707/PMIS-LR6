package com.example.labs5plus.di

import android.app.Application
import androidx.room.Room
import com.example.labs5plus.db.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    //функция для инициализации БД
    fun provideMainDb(App:Application):MainDb {
        return Room.databaseBuilder(
            App,
            MainDb::class.java,
            name = "info.db"
        ).createFromAsset("db/info.db").build()
    }
}