package com.santy.worldofharrypotter.di

import android.content.Context
import androidx.room.Room
import com.santy.worldofharrypotter.data.local.AppDatabase
import com.santy.worldofharrypotter.data.local.dao.BookDao
import com.santy.worldofharrypotter.data.local.dao.CharacterDao
import com.santy.worldofharrypotter.data.local.dao.HouseDao
import com.santy.worldofharrypotter.data.local.dao.SpellDao
import com.santy.worldofharrypotter.data.remote.ApiService
import com.santy.worldofharrypotter.util.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://potterapi-fedeperin.vercel.app/en/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "potter_db"
        ).build()
    }

    @Provides
    fun provideBookDao(db: AppDatabase): BookDao = db.bookDao()

    @Provides
    fun provideCharacterDao(db: AppDatabase): CharacterDao = db.characterDao()

    @Provides
    fun provideHousesDao(db: AppDatabase): HouseDao = db.houseDao()

    @Provides
    fun provideSpellDao(db: AppDatabase): SpellDao = db.spellDao()

    @Provides
    @Singleton
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = NetworkMonitor(context)
}