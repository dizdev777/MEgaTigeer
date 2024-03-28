package evam.interestgames.megatigr.domain

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import evam.interestgames.megatigr.model.Screen
import evam.interestgames.megatigr.ui.screens.GameScreen
import evam.interestgames.megatigr.ui.screens.LoadScreen
import kotlinx.coroutines.delay

@Composable
fun AppComponent(coins:MutableState<Int>,currentScreen:MutableState<Screen>){

    AnimatedVisibility(visible = currentScreen.value == Screen.Load) {
        LoadScreen()
        LaunchedEffect(key1 = Unit, block = {
            delay((500..900).random().toLong())
            currentScreen.value = Screen.Game
        })
    }
    AnimatedVisibility(visible = currentScreen.value == Screen.Game) {
                GameScreen(coins = coins)
    }

}