package com.example.compose_architecture.ui.screen.examples.lazyColumn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.compose_architecture.ui.screen.views.ViewScreen

/**
 * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/paging/PagingSource
 *
 * */
@Composable
fun ViewScreen.ShowLazyColumnWidget(lazyColumnViewModel: LazyColumnViewModel) {

    val photos = lazyColumnViewModel.getPhotoPagination().collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(photos.itemCount) { index ->
            Box {
                photos[index]?.downloadUrl?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 4.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(0.dp)
                            )
                    )
                }
                Text(
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    text = "Clicked by: " + photos[index]?.author.toString(),
                    modifier = Modifier
                        .padding(top = 8.dp, start = 4.dp)
                        .background(color = Color.White)
                        .padding(4.dp)
                )
            }
        }
    }
}
