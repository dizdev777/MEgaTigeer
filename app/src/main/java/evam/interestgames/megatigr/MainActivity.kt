package evam.interestgames.megatigr

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import evam.interestgames.megatigr.domain.AppComponent
import evam.interestgames.megatigr.model.Screen

import evam.interestgames.megatigr.ui.theme.MegaTigrTheme

class MainActivity : ComponentActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("MegaTigr", Context.MODE_PRIVATE)
        val wc = WindowCompat.getInsetsController(window,window.decorView)
        wc.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        wc.hide(WindowInsetsCompat.Type.systemBars())
        setContent {
            MegaTigrTheme {
                val coins = remember {
                    mutableStateOf(sharedPreferences.getInt("coins", 400))
                }
                val currentScreen: MutableState<Screen> = remember {
                    mutableStateOf(Screen.Load)
                }

                AppComponent(coins = coins, currentScreen = currentScreen)

                DisposableEffect(key1 = Unit, effect = {
                    object : DisposableEffectResult {
                        override fun dispose() {
                            sharedPreferences.edit().putInt("coins", coins.value).apply()
                        }

                    }
                })
            }
        }
    }

}
