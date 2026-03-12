package com.example.audioxel.di

import com.example.audioxel.data.repository.Repository
import com.example.audioxel.data.repository.RepositoryImpl
import com.example.audioxel.data.security.ISecureTokenStore
import com.example.audioxel.data.security.SecureTokenStore
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
    abstract fun bindAudioRepository(
        audioRepositoryImpl: RepositoryImpl
    ): Repository

    @Binds
    @Singleton
    abstract fun bindSecureTokenStore(
        secureTokenStore: SecureTokenStore
    ): ISecureTokenStore
}
