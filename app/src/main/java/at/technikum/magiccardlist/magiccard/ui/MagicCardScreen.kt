package at.technikum.magiccardlist.magiccard.ui

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

@Composable
fun MagicCardScreen(
    modifier: Modifier = Modifier,
    magicCardViewModel: MagicCardViewModel = viewModel()
) {

    val state by magicCardViewModel
        .magicCardUiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = { magicCardViewModel.onLoadButtonClicked() },
            enabled = !state.isLoading
        ) {
            Text("Load Cards")
        }
    }
    if (state.isError) {
        Text("Something went wrong", color = Color.Red)
    } else {
        LazyColumn {
            items(state.cards) { card ->
                Text(card.name)
            }
        }
    }
}

