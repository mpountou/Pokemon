package com.intelligent.pokemon.di

import com.intelligent.pokemon.data.remote.PokeApi
import com.intelligent.pokemon.data.remote.repository.PokemonRepository
import com.intelligent.pokemon.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokeApi(): PokeApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)

    @Singleton
    @Provides
    fun providePokemonRepository(pokeApi: PokeApi): PokemonRepository = PokemonRepository(pokeApi)

}