package at.technikum.magiccardlist.magiccard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.technikum.magiccardlist.magiccard.data.MagicCardRepository
import at.technikum.magiccardlist.magiccard.data.dto.MagicCardDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MagiCardUiState(
    val cards: List<MagicCardDto>,
    val isLoading: Boolean,
    val isError: Boolean,
)

class MagicCardViewModel(
    private val magicCardRepository: MagicCardRepository
    = MagicCardRepository()

) : ViewModel() {

    private val _magicCardUiState = MutableStateFlow(
        MagiCardUiState(emptyList(), false, false)
    )
    val magicCardUiState = _magicCardUiState.asStateFlow()

    fun onLoadButtonClicked() {
        _magicCardUiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val cards = magicCardRepository.loadCardPage(1)
            _magicCardUiState.update {
                it.copy(cards = cards, isLoading = false)
            }
        }
    }

}