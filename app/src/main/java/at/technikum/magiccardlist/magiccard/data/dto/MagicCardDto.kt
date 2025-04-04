package at.technikum.magiccardlist.magiccard.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MagicCardDto(
    val name: String,
    val type: String,
    val colors: List<String>,
    val imageUrl: String?,
    val text: String,
)

@Serializable
data class MagicCardListDto(
    val cards: List<MagicCardDto>,
)