package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTextStyle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Home(menuItems: List<MenuItemRoom>) {
    val modifier = Modifier.padding(horizontal = 12.dp)

    val filterCategory = remember { mutableStateOf("") }
    var shownMenuItems: List<MenuItemRoom> = when(filterCategory.value) {
        "starters" -> menuItems.filter { it.category == "starters" }
        "mains" -> menuItems.filter { it.category == "mains" }
        "desserts" -> menuItems.filter { it.category == "desserts" }
        "drinks" -> menuItems.filter { it.category == "drinks" }
        else -> { menuItems }
    }

    val searchPhrase = remember { mutableStateOf("") }
    if(searchPhrase.value.isNotEmpty()) {
        shownMenuItems = shownMenuItems.filter {
            it.title.contains(searchPhrase.value.trim(), ignoreCase = true)
        }
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
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
                    value = searchPhrase.value,
                    leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                    placeholder = {
                        Text(
                            text = "Enter search phrase",
                            color = Color.LightGray,
                            style = LittleLemonTextStyle.paragraphText
                        )
                    },
                    onValueChange = { searchPhrase.value = it },
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

        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 32.dp)
        ) {
            Text(
                text = stringResource(R.string.order_for_delivery),
                color = LittleLemonColor.Charcoal,
                style = LittleLemonTextStyle.sectionTitle,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(rememberScrollState())
            ) {
                FilterButton(category = "starters", width = 90.dp, filterCategory = filterCategory)
                Spacer(modifier = Modifier.width(16.dp))
                FilterButton(category = "mains", width = 80.dp, filterCategory = filterCategory)
                Spacer(modifier = Modifier.width(16.dp))
                FilterButton(category = "drinks", width = 90.dp, filterCategory = filterCategory)
                Spacer(modifier = Modifier.width(16.dp))
                FilterButton(category = "desserts", width = 80.dp, filterCategory = filterCategory)
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = modifier)


        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            this.items(
                items = shownMenuItems,
                itemContent = {menuItem ->
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.75f)
                        ) {
                            Text(
                                text = menuItem.title,
                                color = LittleLemonColor.Charcoal,
                                style = LittleLemonTextStyle.cardTitle,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = menuItem.description,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = LittleLemonColor.Green,
                                style = LittleLemonTextStyle.paragraphText,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "$" + menuItem.price,
                                color = LittleLemonColor.Green,
                                style = LittleLemonTextStyle.leadText,
                            )
                        }

                        Spacer(modifier = Modifier.fillMaxWidth(0.1f))

                        GlideImage(
                            model = menuItem.image,
                            contentDescription = "Image of " + menuItem.title,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .wrapContentHeight()
                                .padding(top = 12.dp)
                                .fillMaxWidth()
                                .aspectRatio(1.0f)
                        )
                    }

                    Divider(color = LittleLemonColor.Cloud, thickness = 1.dp)
                }
            )
        }
    }
}

@Composable
fun FilterButton(category: String, width: Dp, filterCategory: MutableState<String>) {
    OutlinedIconToggleButton(
        checked = filterCategory.value == category,
        onCheckedChange = { filterCategory.value = if(filterCategory.value == category) "" else category },
        modifier = Modifier.width(width),
        colors = IconButtonDefaults.outlinedIconToggleButtonColors(
            containerColor = LittleLemonColor.Cloud,
            contentColor = LittleLemonColor.Green,
            checkedContainerColor = LittleLemonColor.Green,
            checkedContentColor = Color.White
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        interactionSource = remember { MutableInteractionSource() },
        content = {
            Text(
                text = category,
                textAlign = TextAlign.Center,
                style = LittleLemonTextStyle.sectionCategories
            )
        }
    )
}