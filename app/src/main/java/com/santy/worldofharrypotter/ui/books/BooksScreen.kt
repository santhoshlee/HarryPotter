package com.santy.worldofharrypotter.ui.books

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.santy.worldofharrypotter.ui.home.EmptyView

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BooksScreen(viewModel: BooksViewModel = hiltViewModel()) {

    val books by viewModel.books.collectAsState()

    if (books.isEmpty()) {
        EmptyView("No Books Found")
    } else {
        LazyColumn {
            items(books) { book ->
                Row(Modifier.padding(16.dp)) {
                    GlideImage(
                        model = book.cover,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(book.title)
                }
            }
        }
    }
}