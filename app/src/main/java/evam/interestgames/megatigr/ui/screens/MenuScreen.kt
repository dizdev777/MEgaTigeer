package evam.interestgames.megatigr.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import evam.interestgames.megatigr.R

@Composable
fun LoadingScreen(){
    Image(painter = painterResource(id = R.drawable.jungle),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)


    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier){

            CircularProgressIndicator(color = Color(0xFFF3F0F0),
                modifier = Modifier.size(40.dp))

            Text(text = "Loading...",
                color = Color(0xFFF3F0F0), fontSize = 25.sp,
                modifier = Modifier,
                fontFamily = FontFamily.Cursive
            )
        }
    }


}