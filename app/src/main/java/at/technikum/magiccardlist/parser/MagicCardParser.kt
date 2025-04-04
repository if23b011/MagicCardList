package at.technikum.magiccardlist.parser

import at.technikum.magiccardlist.magiccard.data.dto.MagicCardDto
import org.json.JSONObject

class MagicCardParser {
    fun parseJson(json: String): List<MagicCardDto> {
        val cards = mutableListOf<MagicCardDto>()

        try {
            val root = JSONObject(json)
            val cardsArray = root.getJSONArray("cards")

            for (i in 0 until cardsArray.length()) {
                val cardObject = cardsArray.getJSONObject(i)

                val name = cardObject.optString("name", "<unknown>")
                val type = cardObject.optString("type", "")
                val rarity = cardObject.optString("rarity", "")

                // Farben aus dem JSON-Array extrahieren
                val colorsArray = cardObject.optJSONArray("colors")
                val colors = mutableListOf<String>()
                if (colorsArray != null) {
                    for (j in 0 until colorsArray.length()) {
                        colors.add(colorsArray.getString(j))
                    }
                }

                val dto = MagicCardDto(
                    name = name,
                    type = type,
                    rarity = rarity,
                    colors = colors
                )

                cards.add(dto)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return cards
    }
}