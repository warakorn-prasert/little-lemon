package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(context: Context, navController: NavController) {
    val sharedPreferences =
        context.getSharedPreferences("LittleLemon.prefs", Context.MODE_PRIVATE)
    val firstname = sharedPreferences.getString("First name", "") ?: ""
    val lastname = sharedPreferences.getString("Last name", "") ?: ""
    val email = sharedPreferences.getString("Email", "") ?: ""

    var interactionSource = remember { MutableInteractionSource() }
    var isPressed = interactionSource.collectIsPressedAsState()
    var buttonColor = if (isPressed.value) LittleLemonColor.Orange else LittleLemonColor.Yellow
    var buttonTextColor = if (isPressed.value) LittleLemonColor.Cloud else LittleLemonColor.Charcoal

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "Personal information",
            color = LittleLemonColor.Charcoal,
            style = LittleLemonTextStyle.cardTitle,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.height(24.dp))
        FrozenTextField(
            title = "First name",
            value = firstname
        )

        Spacer(modifier = Modifier.height(24.dp))
        FrozenTextField(
            title = "Last name",
            value = lastname
        )

        Spacer(modifier = Modifier.height(24.dp))
        FrozenTextField(
            title = "Email",
            value = email
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedButton(
            onClick = { navController.navigate(Onboarding.route) },
            interactionSource = interactionSource,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, LittleLemonColor.Orange),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = buttonTextColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = "Log out",
                style = LittleLemonTextStyle.leadText
            )
        }

    }
}


@Composable
fun FrozenTextField(
    title: String,
    value: String
) {
    Column {
        Text(
            text = title,
            color = LittleLemonColor.Charcoal,
            style = LittleLemonTextStyle.paragraphText
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            readOnly = true,
            value = value,
            onValueChange = { },
            singleLine = true,
            textStyle = LittleLemonTextStyle.paragraphText,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = LittleLemonColor.Cloud,
                unfocusedIndicatorColor = LittleLemonColor.Cloud,
            ),
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {

}