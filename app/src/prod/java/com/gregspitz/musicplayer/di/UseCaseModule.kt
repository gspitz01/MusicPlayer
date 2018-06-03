package com.gregspitz.musicplayer.di

import com.gregspitz.musicplayer.UseCaseHandler
import com.gregspitz.musicplayer.UseCaseScheduler
import com.gregspitz.musicplayer.UseCaseThreadPoolScheduler
import dagger.Module
import dagger.Provides

/**
 * Dagger module for use case injection
 */
@Module
class UseCaseModule {

    @Provides
    fun provideUseCaseScheduler(): UseCaseScheduler {
        return UseCaseThreadPoolScheduler()
    }

    @Provides
    fun provideUseCaseHandler(useCaseScheduler: UseCaseScheduler): UseCaseHandler {
        return UseCaseHandler(useCaseScheduler)
    }
}
