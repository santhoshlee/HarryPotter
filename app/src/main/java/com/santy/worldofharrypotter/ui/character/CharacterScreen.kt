package com.santy.worldofharrypotter.ui.character


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.santy.worldofharrypotter.data.local.entity.CharacterEntity
import com.santy.worldofharrypotter.ui.character.viewmodels.CharacterViewModel
import com.santy.worldofharrypotter.ui.home.EmptyView

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val characters by viewModel.characters.collectAsState()

    if (characters.isEmpty()) {
        EmptyView("No Character Found")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(
                items = characters,
                key = { it.index }
            ) { character ->
                CharacterItem(character, onItemClick = onItemClick)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(
    character: CharacterEntity,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(8.dp)
                .clickable { onItemClick(character.index) }) {
            GlideImage(
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp)),
                model = character.image,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.height(16.dp))
            Text(
                character.fullName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}