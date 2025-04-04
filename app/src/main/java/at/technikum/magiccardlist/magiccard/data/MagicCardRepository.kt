package at.technikum.magiccardlist.magiccard.data

import at.technikum.magiccardlist.magiccard.data.remote.MagicCardRemoteService
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class MagicCardRepository {
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.magicthegathering.io/v1/")
        .addConverterFactory(json
            .asConverterFactory(MediaType.get("application/json")))
        .build()

    val magicCardRemoteService = retrofit.create(MagicCardRemoteService::class.java)
}