package com.gregspitz.musicplayer.player

import com.gregspitz.musicplayer.BasePresenter
import com.gregspitz.musicplayer.BaseView
import com.gregspitz.musicplayer.data.model.Track

interface PlayerContract {

    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active: Boolean)

        fun showTrack(track: Track)

        fun showFailedToLoadTrack()

        fun getTrackId(): String?

        fun isActive(): Boolean
    }

    interface Presenter : BasePresenter {

        fun loadTrackById(id: String)

        fun loadTrackByName(name: String)
    }
}
