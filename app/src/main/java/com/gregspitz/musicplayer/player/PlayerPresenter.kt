package com.gregspitz.musicplayer.player

import com.gregspitz.musicplayer.UseCase
import com.gregspitz.musicplayer.UseCaseHandler
import com.gregspitz.musicplayer.player.domain.usecase.GetTrackById
import com.gregspitz.musicplayer.player.domain.usecase.GetTrackByName

class PlayerPresenter(private val useCaseHandler: UseCaseHandler,
                      private val playerView: PlayerContract.View,
                      private val getTrackById: GetTrackById,
                      private val getTrackByName: GetTrackByName) : PlayerContract.Presenter {

    init {
        playerView.setPresenter(this)
    }


    override fun loadTrackById(id: String) {
        playerView.setLoadingIndicator(true)

        useCaseHandler.execute(getTrackById, GetTrackById.RequestValues(id),
                object: UseCase.UseCaseCallback<GetTrackById.ResponseValue> {
                    override fun onSuccess(response: GetTrackById.ResponseValue) {
                        if (playerView.isActive()) {
                            playerView.setLoadingIndicator(false)
                            playerView.showTrack(response.track)
                        }
                    }

                    override fun onError() {
                        if (playerView.isActive()) {
                            playerView.setLoadingIndicator(false)
                            playerView.showFailedToLoadTrack()
                        }
                    }
                })
    }

    override fun loadTrackByName(name: String) {
        playerView.setLoadingIndicator(true)

        useCaseHandler.execute(getTrackByName, GetTrackByName.RequestValues(name),
                object: UseCase.UseCaseCallback<GetTrackByName.ResponseValue> {
                    override fun onSuccess(response: GetTrackByName.ResponseValue) {
                        if (playerView.isActive()) {
                            playerView.setLoadingIndicator(false)
                            playerView.showTrack(response.track)
                        }
                    }

                    override fun onError() {
                        if (playerView.isActive()) {
                            playerView.setLoadingIndicator(false)
                            playerView.showFailedToLoadTrack()
                        }
                    }
                })
    }

    override fun start() {
        val trackId = playerView.getTrackId()
        if (trackId != null) {
            loadTrackById(trackId)
        } else {
            playerView.showFailedToLoadTrack()
        }
    }

}
