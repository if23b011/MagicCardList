package at.technikum.magiccardlist.dto

data class MagicCardDto(
    val name: String,
    val type: String,
    val rarity: String,
    val colors: List<String>,
)
