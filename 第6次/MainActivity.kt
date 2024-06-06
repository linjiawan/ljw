package com.example.zxy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.zxy.ui.theme.ZXYTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ZXYTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialThem.colorScheme.background
                ) {
                    InventoryApp()
                }
            }
        }
    }
}