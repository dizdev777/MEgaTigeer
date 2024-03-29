package evam.interestgames.megatigr.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.onesignal.OneSignal
import evam.interestgames.megatigr.R
import evam.interestgames.megatigr.model.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(currentScreen:MutableState<Screen>,music:MutableState<Boolean>,notifications: MutableState<Boolean>){
    Image(painter = painterResource(id = R.drawable.jungle), contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
    val activity = LocalContext.current as Activity
    BackHandler {
        currentScreen.value = Screen.Main
    }
    val scope = rememberCoroutineScope()
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
                    containerColor = Color(0xFFA86605),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "MUSIC: ${if (music.value) "ON" else "OFF"}",
                    fontSize = 19.sp,
                    color = Color(0xFFF3F3F3),
                    fontFamily = FontFamily(Font(R.font.junglebo2))
                )
            }
            Button(
                onClick = {
                    OneSignal.initWithContext(activity, "6db2c88a-84ac-4d48-9a69-d8a5c371713f")
                    scope.launch {
                        OneSignal.Notifications.requestPermission(true)
                    }
                    notifications.value = !notifications.value
                    if(notifications.value){
                        OneSignal.User.pushSubscription.optIn()
                    }
                    else{
                        OneSignal.User.pushSubscription.optOut()
                    }
                },
                modifier = Modifier.size(230.dp, 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA86605),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "NOTIFICATIONS: ${if (notifications.value) "ON" else "OFF"}",
                    fontSize = 19.sp,
                    color = Color(0xFFF3F3F3),
                    fontFamily = FontFamily(Font(R.font.junglebo2))
                )
            }
            Button(
                onClick = {
                    currentScreen.value = Screen.Main
                },
                modifier = Modifier.size(230.dp, 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA86605),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "BACK",
                    fontSize = 19.sp,
                    color = Color(0xFFF3F3F3),
                    fontFamily = FontFamily(Font(R.font.junglebo2))
                )
            }


        }
    }
}