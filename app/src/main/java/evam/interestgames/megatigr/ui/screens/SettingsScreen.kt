package evam.interestgames.megatigr.ui.screens

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import evam.interestgames.megatigr.R
import evam.interestgames.megatigr.model.Screen

@Composable
fun SettingsScreen(currentScreen:MutableState<Screen>,music:MutableState<Boolean>,notifications: MutableState<Boolean>){
    Image(painter = painterResource(id = R.drawable.jungle), contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
    val activity = LocalContext.current as Activity
    BackHandler {
        currentScreen.value = Screen.Main
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
        ) {

            Button(
                onClick = {
                    music.value = !music.value
                },
                modifier = Modifier.size(230.dp, 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF814F06),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "MUSIC: ${if (music.value) "ON" else "OFF"}",
                    fontSize = 19.sp,
                    color = Color(0xFFF3F3F3)
                )
            }
            Button(
                onClick = {

                },
                modifier = Modifier.size(230.dp, 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF814F06),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "NOTIFICATIONS: ${if (notifications.value) "ON" else "OFF"}",
                    fontSize = 19.sp,
                    color = Color(0xFFF3F3F3)
                )
            }
            Button(
                onClick = {
                    currentScreen.value = Screen.Main
                },
                modifier = Modifier.size(230.dp, 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF814F06),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "BACK",
                    fontSize = 19.sp,
                    color = Color(0xFFF3F3F3)
                )
            }


        }
    }
}