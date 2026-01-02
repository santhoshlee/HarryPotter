package com.santy.worldofharrypotter.ui.character.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santy.worldofharrypotter.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: Int? = savedStateHandle["characterId"]

    val character = if (characterId != null) {
        repository.getCharacterById(characterId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    } else {
        MutableStateFlow(null)
    }
}