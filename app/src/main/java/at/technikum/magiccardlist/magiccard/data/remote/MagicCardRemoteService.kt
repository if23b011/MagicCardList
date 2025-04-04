package at.technikum.magiccardlist.magiccard.data.remote

import at.technikum.magiccardlist.magiccard.data.dto.MagicCardListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MagicCardRemoteService {
    @GET("cards")
    suspend fun getCardsPage(@Query("page") page: Int): MagicCardListDto
}