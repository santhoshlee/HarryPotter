package com.santy.worldofharrypotter.ui.books.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santy.worldofharrypotter.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    repository: BookRepository
) : ViewModel() {

    val books = repository.getBooks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


}