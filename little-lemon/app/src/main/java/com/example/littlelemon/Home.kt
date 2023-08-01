package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTextStyle

@Composable
fun Home() {
    val modifier = Modifier.padding(horizontal = 12.dp)

    var search = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(LittleLemonColor.Green)
                .padding(bottom = 8.dp)
        ) {
            Column(modifier = modifier) {
                Text(
                    text = stringResource(R.string.hero_title),
                    color = LittleLemonColor.Yellow,
                    style = LittleLemonTextStyle.displayTitle,
                    modifier = Modifier.offset(y = 0.dp)
                )
                Text(
                    text = stringResource(R.string.hero_subtitle),
                    color = Color.White,
                    style = LittleLemonTextStyle.subtitle,
                    modifier = Modifier.offset(y = (-24).dp)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.hero_detail),
                        color = Color.White,
                        style = LittleLemonTextStyle.leadText,
                        modifier = Modifier
                            .offset(y = (-16).dp)
                            .fillMaxWidth(0.6f)
                    )
                    Image(
                        painterResource(R.drawable.hero_image),
                        contentDescription = "Hero image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .offset(y = (-40).dp)
                            .aspectRatio(1.0f)
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth(0.4f)
                    )
                }
                OutlinedTextField(
                    value = search.value,
                    label = {
                        Text(text = "Enter search phrase", color = LittleLemonColor.Cloud, style = LittleLemonTextStyle.paragraphText)
                    },
                    onValueChange = { search.value = it },
                    singleLine = true,
                    textStyle = LittleLemonTextStyle.paragraphText,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = LittleLemonColor.Cloud,
                        unfocusedIndicatorColor = LittleLemonColor.Cloud,
                    ),
                    modifier = Modifier
                        .offset(y = (-16).dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }

        Column(modifier = modifier) {

        }

        Column(modifier = modifier) {

        }
    }
}