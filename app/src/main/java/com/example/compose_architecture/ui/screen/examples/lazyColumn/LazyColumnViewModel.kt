package com.example.compose_architecture.ui.screen.examples.lazyColumn

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LazyColumnViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    // PhotoPagingSource를 통해 데이터를 불러온다
    fun getPhotoPagination(): Flow<PagingData<Photo>> {
        return Pager(PagingConfig(20)) {
            PhotoPagingSource(photoRepository)
        }.flow
    }
}