package com.intelligent.pokemon.data.remote.repository

import com.intelligent.pokemon.data.remote.PokeApi
import com.intelligent.pokemon.data.remote.responses.Pokemon
import com.intelligent.pokemon.data.remote.responses.PokemonList
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    suspend fun getPokemonList(limit: Int, offset: Int) : PokemonList {
        return api.getPokemonList(limit,offset)
    }

    suspend fun getPokemonInfo(pokemonName: String) : Pokemon {
        return api.getPokemonInfo(pokemonName)
    }
}