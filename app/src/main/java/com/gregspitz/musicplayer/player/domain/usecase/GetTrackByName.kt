package com.gregspitz.musicplayer.player.domain.usecase

import com.gregspitz.musicplayer.UseCase
import com.gregspitz.musicplayer.data.model.Track

class GetTrackByName : UseCase<GetTrackByName.RequestValues, GetTrackByName.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class RequestValues(val name: String) : UseCase.RequestValues

    class ResponseValue(val track: Track) : UseCase.ResponseValue
}
