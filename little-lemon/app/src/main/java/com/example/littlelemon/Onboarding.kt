package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTextStyle


@Composable
fun Onboarding(context: Context, navController: NavHostController) {

    // Section spaces
    val logoVerticalMargin = 24.dp
    val imageHeight = 48.dp
    val textSectionHeight = logoVerticalMargin + imageHeight * 2

    // TextField values
    var firstname = remember { mutableStateOf("") }
    var lastname = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }

    // Button anim
    var interactionSource = remember { MutableInteractionSource() }
    var isPressed = interactionSource.collectIsPressedAsState()
    var buttonColor = if (isPressed.value) LittleLemonColor.Orange else LittleLemonColor.Yellow
    var buttonTextColor = if (isPressed.value) LittleLemonColor.Cloud else LittleLemonColor.Charcoal


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(logoVerticalMargin))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App logo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.height(imageHeight)
        )
        Spacer(modifier = Modifier.height(logoVerticalMargin))

        Text(
            text = "Let's get to know you",
            fontSize = 28.sp,
            color = LittleLemonColor.Cloud,
            style = LittleLemonTextStyle.paragraphText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(textSectionHeight)
                .background(LittleLemonColor.Green)
                .wrapContentHeight(Alignment.CenterVertically)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Text(
                text = "Personal information",
                color = LittleLemonColor.Charcoal,
                style = LittleLemonTextStyle.cardTitle,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(textSectionHeight)
                    .wrapContentHeight(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.height(24.dp))
            RegisterTextField(
                title = "First name",
                value = firstname
            )

            Spacer(modifier = Modifier.height(24.dp))
            RegisterTextField(
                title = "Last name",
                value = lastname
            )

            Spacer(modifier = Modifier.height(24.dp))
            RegisterTextField(
                title = "Email",
                value = email
            )

            Spacer(modifier = Modifier.height(48.dp))

            OutlinedButton(
                onClick = {
                    if(
                        firstname.value.isNotBlank() &&
                        lastname.value.isNotBlank() &&
                        email.value.isNotBlank()
                    ) {
                        val sharedPreferences =
                            context.getSharedPreferences("LittleLemon.prefs", Context.MODE_PRIVATE)
                        sharedPreferences.edit()
                            .putString("First name", firstname.value)
                            .putString("Last name", lastname.value)
                            .putString("Email", email.value)
                            .putBoolean("isLogin", true)
                            .apply()
                        navController.navigate(Home.route)
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_SHORT).show()
                    }
                },
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
                    text = "Register",
                    style = LittleLemonTextStyle.leadText
                )
            }

        }
    }


}


@Composable
fun RegisterTextField(
    title: String,
    value: MutableState<String>
) {
    Column {
        Text(
            text = title,
            color = LittleLemonColor.Charcoal,
            style = LittleLemonTextStyle.paragraphText
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value.value,
            onValueChange = { value.value = it },
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
fun OnboardingPreview() {

}