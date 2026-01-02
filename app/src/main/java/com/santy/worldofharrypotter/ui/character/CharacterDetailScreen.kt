package com.santy.worldofharrypotter.ui.character

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
import com.santy.worldofharrypotter.ui.character.viewmodels.CharacterDetailViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailScreen(
    onBack: () -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val character by viewModel.character.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        character?.let { item ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                // Character Image
                GlideImage(
                    model = item.image,
                    contentDescription = item.fullName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                Spacer(Modifier.height(16.dp))

                // Name
                Text(
                    text = item.fullName,
                    style = MaterialTheme.typography.headlineMedium
                )

                // Nickname
                if (item.nickname.isNotBlank()) {
                    Text(
                        text = "Nickname: ${item.nickname}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Hogwarts House
                Text(
                    text = "House: ${item.hogwartsHouse}",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Actor
                Text(
                    text = "Portrayed by: ${item.interpretedBy}",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Birthdate
                Text(
                    text = "Born: ${item.birthdate}",
                    style = MaterialTheme.typography.bodyMedium
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                // Children Section
                if (item.children.isNotEmpty()) {
                    Text(
                        text = "Children",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(Modifier.height(4.dp))

                    item.children.forEach { child ->
                        Text(
                            text = "• $child",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        } ?: run {
            // Loading state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
