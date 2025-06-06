package com.example.sosabrigos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sosabrigos.ui.theme.SosAbrigosTheme
import com.example.sosabrigos.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SosAbrigosTheme {
                AppNavHost()
            }
        }

    }
}
