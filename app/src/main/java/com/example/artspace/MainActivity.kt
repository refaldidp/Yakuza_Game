package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
private fun ArtSpace() {

    var artworkIndex by remember { mutableStateOf(0) }
    if(artworkIndex == 4) artworkIndex = 0
    if(artworkIndex == -1) artworkIndex = 3

    val artwork: Int
    val artworkDescription: Int
    val artworkName: Int
    val artworkReleaseYear: Int
    val artworkGenre: Int

    when (artworkIndex) {
        0 -> {
            artwork = R.drawable.yakuza_0
            artworkDescription = R.string.yakuza_0_description
            artworkName = R.string.yakuza_0_title
            artworkReleaseYear = R.string.duarebulimabelas
            artworkGenre = R.string.yakuza_0
        }
        1 -> {
            artwork = R.drawable.yakuza_1
            artworkDescription = R.string.yakuza_1_description
            artworkName = R.string.yakuza_1_title
            artworkReleaseYear = R.string.duarebulima
            artworkGenre = R.string.yakuza_0
        }
        2 -> {
            artwork = R.drawable.yakuza_4
            artworkDescription = R.string.yakuza_4_description
            artworkName = R.string.yakuza_4_title
            artworkReleaseYear = R.string.duarebusembilan
            artworkGenre = R.string.yakuza_0

        }
        else -> {
            artwork = R.drawable.yakuza_dragon
            artworkDescription = R.string.yakuza_dragon_description
            artworkName = R.string.yakuza_dragon_title
            artworkReleaseYear = R.string.duarebuduapuluh
            artworkGenre = R.string.yakuza_0
        }
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.card_white)),
            shape = RectangleShape,
        ) {
            Image(
                painter = painterResource(id = artwork),
                contentDescription = stringResource(id = artworkDescription),
                modifier = Modifier.padding(20.dp),
            )
        }

        BoxWithConstraints {
            if(maxWidth < 413.dp) {
                Spacer(Modifier.height(76.dp))
            } else if (maxWidth < 1281.dp) {
                Spacer(Modifier.height(20.dp))
            }
        }

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(colorResource(id = R.color.artwork_title_black), fontWeight = FontWeight.Light, fontSize = 24.sp)
                ) {
                    append(stringResource(id = artworkName))
                }

                withStyle(
                    style = SpanStyle(colorResource(id = R.color.artwork_author_black), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                ) {
                    append(stringResource(id = artworkGenre))
                }

                withStyle(
                    style = SpanStyle(colorResource(id = R.color.artwork_release_year_black),  fontWeight = FontWeight.Light, fontSize = 16.sp)
                ) {
                    append(stringResource(id = artworkReleaseYear))
                }
            },

            modifier = Modifier
                .background(colorResource(id = R.color.artwork_info_background))
                .padding(16.dp),
        )

        Spacer(Modifier.height(55.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            EditButtons(
                onClick = { artworkIndex-- },
                buttonColor = R.color.button_blue,
                buttonText = R.string.previous_button,
            )

            EditButtons(
                onClick = { artworkIndex++ },
                buttonColor = R.color.button_blue,
                buttonText = R.string.next_button,
            )
        }
    }
}

@Composable
fun EditButtons(onClick: () -> Unit, @StringRes buttonText: Int, @ColorRes buttonColor: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(colorResource(id = buttonColor)),
        modifier = modifier.width(140.dp),
    ) {
        Text(text = stringResource(id = buttonText))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}