package evam.interestgames.megatigr

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import evam.interestgames.megatigr.domain.AppComponent
import evam.interestgames.megatigr.model.Screen

import evam.interestgames.megatigr.ui.theme.MegaTigrTheme

class MainActivity : ComponentActivity() {
    lateinit var sharedPreferences: SharedPreferences
lateinit var musicPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("MegaTigr", Context.MODE_PRIVATE)
        val wc = WindowCompat.getInsetsController(window,window.decorView)
        wc.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        wc.hide(WindowInsetsCompat.Type.systemBars())
         musicPlayer = MediaPlayer.create(this,R.raw.music)
        musicPlayer.isLooping = true
        setContent {
            MegaTigrTheme {
                val music = remember{
                mutableStateOf(true)
            }
                val coins = remember {
                    mutableStateOf(sharedPreferences.getInt("coins", 400))
                }
                val currentScreen: MutableState<Screen> = remember {
                    mutableStateOf(Screen.Load)
                }
                val notifications =  remember{
                mutableStateOf(false)
            }
                LaunchedEffect(key1 = music.value, block = {

                    if(music.value){
                        musicPlayer.seekTo(0)
                        musicPlayer.start()
                    }
                    else{
                        musicPlayer.pause()
                    }
                })
                AppComponent(coins = coins, currentScreen = currentScreen,music,notifications)

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

    override fun onPause() {
        musicPlayer.pause()
        super.onPause()
    }

    override fun onResume() {
        musicPlayer.start()
        super.onResume()
    }

}
