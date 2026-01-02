package com.santy.worldofharrypotter.ui.spell.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santy.worldofharrypotter.data.repository.SpellRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SpellsViewModel @Inject constructor(
    repository: SpellRepository
) : ViewModel() {
    val spells = repository.getSpells()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}