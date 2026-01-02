package com.santy.worldofharrypotter.ui.books

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.santy.worldofharrypotter.ui.books.viewmodels.BookDetailViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun BookDetailScreen (
    onBack: () -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val book by viewModel.book.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        book?.let { item ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()) // Allow scrolling for long descriptions
            ) {
                GlideImage(
                    model = item.cover,
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(text = item.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = "Pages: ${item.pages}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Released: ${item.releaseDate}", style = MaterialTheme.typography.bodyMedium)

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                Text(text = "Description", style = MaterialTheme.typography.titleMedium)
                Text(text = item.description, style = MaterialTheme.typography.bodyLarge)
            }
        } ?: run {
            // Show a loader if book is null
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}