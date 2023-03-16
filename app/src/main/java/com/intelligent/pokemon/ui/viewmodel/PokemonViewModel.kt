package com.intelligent.pokemon.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.pokemon.data.remote.repository.PokemonRepository
import com.intelligent.pokemon.model.Pokemon
import com.intelligent.pokemon.model.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<RequestState<List<Pokemon>>>(RequestState.Initial)
    val pokemonList: StateFlow<RequestState<List<Pokemon>>> = _pokemonList

    private val _term = MutableLiveData<String>("")
    val term: LiveData<String> = _term

    fun filterPokemonByTerm(pokemonList: List<Pokemon>, term: String): List<Pokemon> {
        if (term.trim().isEmpty()) {
            return pokemonList
        }
        return pokemonList.filter { it.pokemonName.toLowerCase().contains(term.toLowerCase()) }
            .toList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _pokemonList.value = RequestState.Loading
            try {
                val result = repository.getPokemonList(10000, 0).results
                val pokemonList: ArrayList<Pokemon> = arrayListOf()
                result.forEach { pokemon ->
                    val number = if (pokemon.url.endsWith("/")) {
                        pokemon.url.dropLast(1).takeLastWhile { it.isDigit() }
                    } else {
                        pokemon.url.takeLastWhile { it.isDigit() }
                    }
                    val url =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                    pokemonList.add(
                        Pokemon(
                            pokemon.name.capitalize(Locale.ROOT),
                            url,
                            number.toInt()
                        )
                    )
                }
                _pokemonList.value =
                    RequestState.Success<List<Pokemon>>(data = pokemonList.toList())
            } catch (e: Exception) {
                _pokemonList.value = RequestState.Error(e)
            }
        }
    }

    fun updateTerm(term: String) {
        _term.value = term
    }

    private val _pokemonInfo = MutableStateFlow<RequestState<com.intelligent.pokemon.data.remote.responses.Pokemon>>(RequestState.Initial)
    val pokemonInfo: StateFlow<RequestState<com.intelligent.pokemon.data.remote.responses.Pokemon>> = _pokemonInfo

    fun getPokemonInfo(pokemonName: String) {
        viewModelScope.launch {
            try {
                _pokemonInfo.value = RequestState.Loading
                val result = repository.getPokemonInfo(pokemonName = pokemonName.toLowerCase())
                _pokemonInfo.value = RequestState.Success(data = result)
            } catch (e: Exception) {
                _pokemonInfo.value = RequestState.Error(e)
            }
        }
    }

}