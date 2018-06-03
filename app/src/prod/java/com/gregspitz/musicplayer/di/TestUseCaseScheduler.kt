package com.gregspitz.musicplayer.di

import com.gregspitz.musicplayer.UseCase
import com.gregspitz.musicplayer.UseCaseScheduler

/**
 * A scheduler that runs synchronously, for testing purposes.
 */
class TestUseCaseScheduler : UseCaseScheduler {
    override fun execute(runnable: Runnable) {
        runnable.run()
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(
            response: V, useCaseCallback: UseCase.UseCaseCallback<V>) {
        useCaseCallback.onSuccess(response)
    }

    override fun <V : UseCase.ResponseValue> onError(useCaseCallback: UseCase.UseCaseCallback<V>) {
        useCaseCallback.onError()
    }

}
