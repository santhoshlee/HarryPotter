package com.santy.worldofharrypotter.ui.houses.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santy.worldofharrypotter.data.repository.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HousesViewModel @Inject constructor(
    repository: HouseRepository
) : ViewModel() {
    val houses = repository.getHouses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}