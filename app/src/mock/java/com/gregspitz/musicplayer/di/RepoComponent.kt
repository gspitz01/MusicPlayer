package com.gregspitz.musicplayer.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RepoModule::class)])
interface RepoComponent {

//    fun exposeRepository(): GameRepository
//
//    fun exposeLocalDataSource(): FakeGameLocalDataSource
}
