package com.example.compose_architecture.ui.screen.functions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose_architecture.model.screen.FunctionScreens

object FunctionScreen {
    /**
     * FunctionView 표시
     * */
    @Composable
    fun ShowFunctionScreen(mainActivityNavHostController: NavHostController) {
        LazyColumn {
            items(count = FunctionScreens.values().size) { index ->
                Text(
                    text = FunctionScreens.values()[index].name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .clickable {
                            mainActivityNavHostController.navigate(FunctionScreens.values()[index].name)
                        }
                        .padding(20.dp)
                        .fillMaxWidth()
                )
                Divider(color = Color.Gray, thickness = 4.dp)
            }
        }
    }
}