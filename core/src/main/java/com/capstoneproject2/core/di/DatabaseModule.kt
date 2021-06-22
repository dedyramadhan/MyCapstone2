package com.capstoneproject2.core.di

import android.content.Context
import androidx.room.Room
import com.capstoneproject2.core.data.source.local.room.MovieDao
import com.capstoneproject2.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
class DatabaseModule {

    @Provides
    fun providesGameDao(database: MovieDatabase): MovieDao = database.movieDao()

    @CoreScope
    @Provides
    fun provideDatabase(context: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("Movies".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

}