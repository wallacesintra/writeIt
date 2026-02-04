package com.sintra.writy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sintra.writeit.PainterCanvas
import com.sintra.writy.ui.theme.WrityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WrityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
                    PainterCanvas(modifier = Modifier.padding(innerpadding))

                }
            }
        }
    }
}