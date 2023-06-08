package com.example.compose_architecture.screen.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose_architecture.model.screen.MainScreens
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_ArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn {
                        items(count = MainScreens.values().size) { index ->
                            Text(
                                text = MainScreens.values()[index].name,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .clickable {

                                    }
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            )
                            Divider(color = Color.Gray, thickness = 4.dp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainList() {

}
