package com.santy.worldofharrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.santy.worldofharrypotter.ui.RootApp
import com.santy.worldofharrypotter.ui.splash.SplashViewModel
import com.santy.worldofharrypotter.ui.theme.WorldOfHarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val splashViewModel: SplashViewModel = hiltViewModel()

            splashScreen.setKeepOnScreenCondition {
                splashViewModel.isLoading
            }
            WorldOfHarryPotterTheme {
                RootApp()
            }
        }
    }
}

