package com.gregspitz.musicplayer.player.domain.usecase

import com.gregspitz.musicplayer.UseCase
import com.gregspitz.musicplayer.data.model.Track

class GetTrackById : UseCase<GetTrackById.RequestValues, GetTrackById.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class RequestValues(val id: String) : UseCase.RequestValues

    class ResponseValue(val track: Track) : UseCase.ResponseValue
}
