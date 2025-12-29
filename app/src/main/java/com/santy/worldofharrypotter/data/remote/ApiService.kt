package com.santy.worldofharrypotter.data.remote

import com.santy.worldofharrypotter.data.local.entity.BookEntity
import com.santy.worldofharrypotter.data.local.entity.CharacterEntity
import com.santy.worldofharrypotter.data.local.entity.HouseEntity
import com.santy.worldofharrypotter.data.local.entity.SpellEntity
import retrofit2.http.GET

interface ApiService {

    @GET("books")
    suspend fun getBooks(): List<BookEntity>

    @GET("characters")
    suspend fun getCharacter(): List<CharacterEntity>

    @GET("houses")
    suspend fun getHouses(): List<HouseEntity>

    @GET("spells")
    suspend fun getSpells(): List<SpellEntity>

}