package at.technikum.magiccardlist.magiccard.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import at.technikum.magiccardlist.magiccard.data.dto.MagicCardDto

@Composable
fun MagicCardScreen(
    modifier: Modifier = Modifier, magicCardViewModel: MagicCardViewModel = viewModel()
) {

    val state by magicCardViewModel.magicCardUiState.collectAsStateWithLifecycle()
    BackHandler(state.selectedCard != null) {
        magicCardViewModel.reset()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(64.dp)
    ) {
        Button(
            onClick = { magicCardViewModel.onLoadButtonClicked() }, enabled = !state.isLoading
        ) {
            Text("Load Cards")
        }
        if (state.isError) {
            Text("Something went wrong", color = Color.Red)
        } else {
            if (state.selectedCard != null) {
                CardDetail(card = state.selectedCard!!)
            } else {
                LazyColumn {
                    items(state.cards) { card ->
                        Text(
                            card.name, Modifier.clickable {
                                magicCardViewModel.onCardClicked(card)
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun CardDetail(modifier: Modifier = Modifier, card: MagicCardDto) {
    Column(modifier = modifier) {
        Text(card.name)
        card.text?.let { Text(it) }
        card.imageUrl?.let { Text(it) }
        for (color in card.colors) {
            Text(color)
        }
    }
}

