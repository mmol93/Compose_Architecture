package com.example.compose_architecture.ui.screen.examples.lazyColumn

/**
 *
 * */
class PhotoRepository(private val apiService: PhotoApiService) {
    // API를 통해 PhotoPagingSource에 데이터를 추가한다.
    suspend fun getPhotos(page: Int): List<Photo> {
        return apiService.getPhotos(page)
    }
}