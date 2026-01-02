package com.santy.worldofharrypotter.ui.character.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santy.worldofharrypotter.data.repository.BookRepository
import com.santy.worldofharrypotter.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacter()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


}