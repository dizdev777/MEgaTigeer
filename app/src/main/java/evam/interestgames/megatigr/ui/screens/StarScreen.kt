package evam.interestgames.megatigr.ui.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import evam.interestgames.megatigr.R
import evam.interestgames.megatigr.model.Screen
import kotlin.math.floor

@Composable
fun StarScreen(currentScreen:MutableState<Screen>){
    Image(painter = painterResource(id = R.drawable.jungle), contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

    Box(modifier = Modifier.padding(32.dp)
        .background(Color(0xFF107214), RoundedCornerShape(32.dp)), contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)){
            Text(text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,)
            Text(text =
                    "Privacy Policy for the game \"Mega Tiger\":\n" +
                    "\n" +
                    "We, the developers of the game \"Mega Tiger\", value your privacy and are committed to protecting your data. Our game does not collect any sensitive information about users. We do not gather personal data such as names, email addresses, or phone numbers.\n" +
                    "\n" +
                    "We do not share, sell, or exchange your personal information with third parties. ",
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,)
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Please, rate the game!",
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,)
            IJoqwjrqwrjijo()
            Button(
                onClick = {
                    currentScreen.value = Screen.Main
                },
                modifier = Modifier.size(210.dp,60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  Color(0xFF814F06),
                    contentColor = Color(0xFFF3F3F3)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "BACK",
                    fontSize = 29.sp,
                    color = Color(0xFFF3F3F3)
                )
            }

        }
        BackHandler {
            currentScreen.value = Screen.Main
        }
    }

}
@Composable
fun IJoqwjrqwrjijo(
    rhqiur82ij: Modifier = Modifier,
    ruqwijriqwiri: Double = 5.0,
    rqwijrhqwru: Color = Color.Yellow,
) {
    val fijqwjriqwir = floor(ruqwijriqwiri).toInt()
    val rquwrqwurui = LocalContext.current as Activity
    Row(modifier = rhqiur82ij.then(
        Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=evam.interestgames.megatigr")
                )
                rquwrqwurui.startActivity(intent)
            })) {
        repeat(fijqwjriqwir) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = rqwijrhqwru,
                modifier = Modifier.size(48.dp))
        }

    }
}