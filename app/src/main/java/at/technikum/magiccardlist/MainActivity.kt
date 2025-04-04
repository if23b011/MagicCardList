package at.technikum.magiccardlist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.technikum.magiccardlist.magiccard.data.dto.MagicCardDto
import at.technikum.magiccardlist.magiccard.ui.MagicCardScreen
import at.technikum.magiccardlist.ui.theme.MagicCardListTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MagicCardListTheme {
                MagicCardScreen()
            }
        }
    }
}

@Composable
fun MagicCardListApp() {
    Scaffold(
        topBar = {
            Surface(
                color = Color(0xFF6200EE),
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier.padding(16.dp),
                )
            }
        }) { innerPadding ->
        MagicCardScreen(modifier = Modifier.padding(innerPadding))
    }
}

/*@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var cardListText by rememberSaveable { mutableStateOf("") }
    var buttonEnabled by rememberSaveable { mutableStateOf(true) }
    var currentPage by rememberSaveable { mutableIntStateOf(1) } //last page is 937

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                cardListText = ""
                buttonEnabled = false
                coroutineScope.launch {
                    val magicCardParser = MagicCardParser()
                    try {
                        cardListText = withContext(Dispatchers.IO) {
                            loadCards(
                                "https://api.magicthegathering.io/v1/cards?page=", currentPage
                            )
                        }

                        val magicCard = withContext(Dispatchers.Default) {
                            magicCardParser.parseJson(cardListText)
                        }

                        cardListText = magicCard.joinToString(separator = "\n") { card ->
                            "${card.name}, ${card.type}, ${card.colors.joinToString()}"
                        }

                        currentPage++
                    } catch (e: Exception) {
                        cardListText = "Error: ${e.message}"
                        Log.e("MainActivity", "Error loading cards: ${e.message}")
                    } finally {
                        buttonEnabled = true
                    }
                }
            }, modifier = Modifier.fillMaxWidth(), enabled = buttonEnabled
        ) {
            Text(text = "Load Cards")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = cardListText,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())

        )
    }
}

fun loadCards(url: String, page: Int): String {
    // https://api.magicthegathering.io/v1/cards?page=
    val urlObject = URL(url + page)
    val con = urlObject.openConnection() as HttpURLConnection
    try {
        con.requestMethod = "GET"
        con.readTimeout = 5000
        con.connectTimeout = 5000
        val result = String(con.inputStream.readBytes())
        val isEmpty = result.contains("\"cards\":[]")
        if (isEmpty) {
            return loadCards(url, 1)
        }
        return result
    } catch (e: Exception) {
        Log.e("MainActivity", "Error loading cards: ${e.message}")
        throw Exception("Error loading cards: ${e.message}")
    } finally {
        con.disconnect()
    }
}
*/

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    MagicCardListTheme {
        MagicCardListApp()
    }
}