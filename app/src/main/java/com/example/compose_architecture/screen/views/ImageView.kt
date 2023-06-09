package com.example.compose_architecture.screen.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme

class ImageView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Compose_ArchitectureTheme() {
                Surface() {
                    Column() {
                        Text(text = "fda")
                    }
                }
            }
        }
    }
}