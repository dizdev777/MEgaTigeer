package evam.interestgames.megatigr.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import evam.interestgames.megatigr.R
import evam.interestgames.megatigr.model.Screen

@Composable
fun MainScreen(currentScreen: MutableState<Screen>){
    val activity = LocalContext.current as Activity
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ){
        Image(painter = painterResource(id = R.drawable.splashpng),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier){

            Button(
                onClick = {
                    currentScreen.value = Screen.Game
                },
                modifier = Modifier.size(210.dp,60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(0xFFA86605),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "PLAY",
                    fontSize = 29.sp,
                    color = Color(0xFFF3F3F3),
                    fontFamily = FontFamily(Font(R.font.junglebo2))
                )
            }
            Button(
                onClick = {
                    currentScreen.value = Screen.Settings
                },
                modifier = Modifier.size(210.dp,60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(0xFFA86605),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "SETTINGS",
                    fontSize = 29.sp,
                    color = Color(0xFFF3F3F3),
                    fontFamily = FontFamily(Font(R.font.junglebo2))
                )
            }
            Button(
                onClick = {
                    currentScreen.value = Screen.Policy
                },
                modifier = Modifier.size(210.dp,60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(0xFFA86605),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "POLICY",
                    fontSize = 29.sp,
                    color = Color(0xFFF3F3F3),
                    fontFamily = FontFamily(Font(R.font.junglebo2))
                )
            }
            Button(
                onClick = {

                    activity.finishAffinity()
                },
                modifier = Modifier.size(210.dp,60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(0xFFA86605),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "EXIT",
                    fontSize = 29.sp,
                    color = Color(0xFFF3F3F3),
                    fontFamily = FontFamily(Font(R.font.junglebo2))
                )
            }


        }

    }


}
