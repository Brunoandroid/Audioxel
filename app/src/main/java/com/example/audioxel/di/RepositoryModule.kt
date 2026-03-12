package com.example.audioxel.di

import com.example.audioxel.data.repository.HomeRepository
import com.example.audioxel.data.repository.HomeRepositoryImpl
import com.example.audioxel.data.repository.soundcloud.SoundCloudRepository
import com.example.audioxel.data.repository.soundcloud.SoundCloudRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindSoundCloudRepository(
        soundCloudRepositoryImpl: SoundCloudRepositoryImpl
    ): SoundCloudRepository
}
