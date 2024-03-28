package evam.interestgames.megatigr.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import evam.interestgames.megatigr.R
import evam.interestgames.megatigr.model.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GameScreen(coins:MutableState<Int>,current:MutableState<Screen>){

    Image(painter = painterResource(id = R.drawable.jungle),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

    val bet = remember{
        mutableStateOf(10)}

    val tigers = remember{
        mutableIntStateOf(5)
    }
    val allTigers = remember{
        listOf(
            R.drawable.tiger1,
            R.drawable.tiger2,
            R.drawable.tiger3,
            R.drawable.tiger4,
            R.drawable.tiger5,
            R.drawable.tiger6,
            R.drawable.tiger7,
            R.drawable.tiger8,
            R.drawable.tiger9,
            )
    }

    val nulls = remember{
        listOf(null,null,null,null,null,null,null,null,null)
    }

    val items = remember{
        mutableStateOf(allTigers.shuffled().take(tigers.value) + nulls.take(9-tigers.value))
    }
    val prize=  remember{
        mutableStateOf(0.0)
    }
    val context  = LocalContext.current
    val openedTigers = remember{
        mutableStateOf(0)
    }
    val gameRunning = remember{
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        LazyVerticalGrid(columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.size(290.dp),
            content = {
                items(items.value){ png->

                    val showJungle = remember{
                        mutableStateOf(true)
                    }
                    LaunchedEffect(key1 = gameRunning.value, block = {
                        if(!gameRunning.value){
                            showJungle.value = true
                        }
                    })


                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(80.dp)

                            .clickable {
                                if (gameRunning.value) {

                                    showJungle.value = false
                                    if (png == null) {
                                        scope.launch {
                                            delay(100)
                                            withContext(Dispatchers.Main) {
                                                Toast
                                                    .makeText(context, "Oops!", Toast.LENGTH_SHORT)
                                                    .show()
                                            }

                                            gameRunning.value = false
                                            prize.value = 0.0
                                        }
                                    } else {
                                        prize.value += (9.0 - tigers.value) / 7.0
                                        openedTigers.value++
                                        if (openedTigers.value == tigers.value) {
                                            scope.launch {
                                                withContext(Dispatchers.Main) {
                                                    Toast
                                                        .makeText(
                                                            context,
                                                            "Win!",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                        .show()
                                                }

                                            }

                                            coins.value += (prize.value * bet.value).toInt()
                                            gameRunning.value = false
                                            prize.value = 0.0

                                        }
                                    }
                                }

                            }){

                        if(png!=null){
                            Image(painter = painterResource(id = png) , contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                            )
                        }
                        AnimatedVisibility(visible =showJungle.value ) {
                            Image(painter = painterResource(id = R.drawable.junlesq) , contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp)))
                        }

                    }



                }




            } )


        Row(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(16.dp)
            .padding(bottom = 118.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom){

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)){
                Button(
                    onClick = {
                        if(coins.value>=bet.value)
                      bet.value++
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  Color(0xFF5E3903),
                        contentColor = Color(0xFFF3F3F3)
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "+",
                        fontSize = 19.sp,
                        color = Color(0xFFF3F3F3)
                    )
                }
                Text(text = "BET: ${bet.value}",
                    fontSize = 22.sp,
                    color = Color(0xFFF3F3F3),
                    modifier = Modifier
                        .background(
                            Color(0xFF5E3903),
                            RoundedCornerShape(4.dp)
                        )
                        .padding(4.dp))
                Button(
                    onClick = {
                        if(coins.value>0)
                            bet.value--
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5E3903),
                        contentColor = Color(0xFFF3F3F3)
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "-",
                        fontSize = 19.sp,
                        color = Color(0xFFF3F3F3)
                    )
                }

            }
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)){


                Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = {
                            if(tigers.value>1)
                                tigers.value--
                        },
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5E3903),
                            contentColor = Color(0xFFF3F3F3)
                        ),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "-",
                            fontSize = 19.sp,
                            color = Color(0xFFF3F3F3)
                        )
                    }
                    Text(text = "TIGERS: ${tigers.value}",
                        fontSize = 18.sp,
                        color = Color(0xFFF3F3F3),
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier
                            .background(
                                Color(0xFF5E3903),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp))

                    Button(
                        onClick = {
                            if(tigers.value<9)
                                tigers.value++
                        },
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor =  Color(0xFF5E3903),
                            contentColor = Color(0xFFF3F3F3)
                        ),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "+",
                            fontSize = 19.sp,
                            color = Color(0xFFF3F3F3)
                        )
                    }

                }

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier){
                    Text(text = "COINS: ${coins.value}",
                        fontSize = 17.sp,
                        color = Color(0xFFF3F3F3),
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier
                            .background(
                                Color(0xFF5E3903),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp))
                    Text(text = "PRIZE: ${(prize.value*bet.value).toInt()}",
                        fontSize = 17.sp,
                        color = Color(0xFFF3F3F3),
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier
                            .background(
                                Color(0xFF5E3903),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp))

                }

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically){

                        Button(
                            onClick = {
                                        if(coins.value>=bet.value){
                                            items.value =
                                                allTigers.shuffled()
                                                    .take(tigers.value) + nulls.take(9 - tigers.value)
                                            gameRunning.value = true
                                            prize.value = 0.0
                                            coins.value -= bet.value
                                        }

                            },
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (!gameRunning.value) Color(0xFF5E3903) else Color(
                                    0xFF7A7473
                                ),
                                contentColor = Color(0xFFF3F3F3)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "PLAY",
                                fontSize = 29.sp,
                                color = Color(0xFFF3F3F3),
                                fontFamily = FontFamily.Cursive
                            )
                        }
                        Button(
                            onClick = {
                                scope.launch {
                                    withContext(Dispatchers.Main) {
                                        Toast
                                            .makeText(context, "Good! ", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }

                                coins.value += (prize.value*bet.value).toInt()

                                gameRunning.value = false
                                prize.value = 0.0
                            },
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (gameRunning.value) Color(0xFF5E3903) else Color(
                                    0xFF7A7473
                                ),
                                contentColor = Color(0xFFF3F3F3)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "STOP",
                                fontSize = 29.sp,
                                color = Color(0xFFF3F3F3),
                                fontFamily = FontFamily.Cursive
                            )
                        }
                    }
            }
        }



    }



    BackHandler {
        current.value = Screen.Main
    }







}