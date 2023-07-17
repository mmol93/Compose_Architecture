package com.example.compose_architecture.di

import com.example.compose_architecture.ui.screen.examples.lazyColumn.PhotoApiService
import com.example.compose_architecture.ui.screen.examples.lazyColumn.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun providePhotoRepository(photoApiService: PhotoApiService): PhotoRepository {
        return PhotoRepository(photoApiService)
    }

    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoApiService {
        return retrofit.create(PhotoApiService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}