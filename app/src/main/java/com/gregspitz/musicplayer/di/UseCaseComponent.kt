package com.gregspitz.musicplayer.di

import dagger.Component

/**
 * Dagger component for use case injection
 */
@UseCaseScope
@Component(modules = [(UseCaseModule::class)], dependencies = [RepoComponent::class])
interface UseCaseComponent {

}
