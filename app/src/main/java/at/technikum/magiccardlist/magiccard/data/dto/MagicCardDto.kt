package at.technikum.magiccardlist.magiccard.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MagicCardDto(
    val name: String,
    val types: List<String>,
    val colors: List<String> = emptyList(),
    val imageUrl: String?,
    val text: String,
)

@Serializable
data class MagicCardListDto(
    val cards: List<MagicCardDto>,
)