package evam.interestgames.megatigr.domain

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import evam.interestgames.megatigr.model.Screen
import evam.interestgames.megatigr.ui.screens.GameScreen
import evam.interestgames.megatigr.ui.screens.LoadingScreen
import evam.interestgames.megatigr.ui.screens.MainScreen
import evam.interestgames.megatigr.ui.screens.SettingsScreen
import evam.interestgames.megatigr.ui.screens.StarScreen
import kotlinx.coroutines.delay

@Composable
fun AppComponent(coins:MutableState<Int>,currentScreen:MutableState<Screen>,music: MutableState<Boolean>,notifications: MutableState<Boolean>){


    AnimatedVisibility(visible = currentScreen.value == Screen.Load) {
        LoadingScreen()
        LaunchedEffect(key1 = Unit, block = {
            delay((500..900).random().toLong())
            currentScreen.value = Screen.Main
        })
    }
    AnimatedVisibility(visible = currentScreen.value == Screen.Settings,
        enter =
        fadeIn(tween(400)),
        exit = fadeOut(tween(400))) {
        SettingsScreen(currentScreen = currentScreen,music,notifications)
    }
    AnimatedVisibility(visible = currentScreen.value == Screen.Main,
        enter =
        fadeIn(tween(400)),
        exit = fadeOut(tween(400))) {
        MainScreen(currentScreen = currentScreen)
    }
    AnimatedVisibility(visible = currentScreen.value == Screen.Policy,
        enter =
        fadeIn(tween(400)),
        exit = fadeOut(tween(400))) {
        StarScreen(currentScreen = currentScreen)
    }
    AnimatedVisibility(visible = currentScreen.value == Screen.Game,
        enter =
        fadeIn(tween(400)),
        exit = fadeOut(tween(400))) {
                GameScreen(coins = coins,currentScreen)
    }

}