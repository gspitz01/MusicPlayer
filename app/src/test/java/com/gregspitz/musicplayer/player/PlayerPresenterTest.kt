package com.gregspitz.musicplayer.player

import com.gregspitz.musicplayer.TestData.TRACK
import com.gregspitz.musicplayer.UseCase
import com.gregspitz.musicplayer.UseCaseHandler
import com.gregspitz.musicplayer.player.domain.usecase.GetTrackById
import com.gregspitz.musicplayer.player.domain.usecase.GetTrackByName
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlayerPresenterTest {

    private val getTrackById: GetTrackById = mock()

    private val byIdRequestCaptor =
            argumentCaptor<GetTrackById.RequestValues>()

    private val byIdCallbackCaptor =
            argumentCaptor<UseCase.UseCaseCallback<GetTrackById.ResponseValue>>()

    private val byIdResponse = GetTrackById.ResponseValue(TRACK)

    private val getTrackByName: GetTrackByName = mock()

    private val byNameRequestCaptor =
            argumentCaptor<GetTrackByName.RequestValues>()

    private val byNameCallbackCaptor =
            argumentCaptor<UseCase.UseCaseCallback<GetTrackByName.ResponseValue>>()

    private val byNameResponse = GetTrackByName.ResponseValue(TRACK)

    private val playerView: PlayerContract.View = mock()

    private val viewInOrder = inOrder(playerView)

    private val useCaseHandler: UseCaseHandler = mock()

    private lateinit var playerPresenter: PlayerPresenter

    @Before
    fun setup() {
        whenever(playerView.isActive()).thenReturn(true)
        playerPresenter = PlayerPresenter(useCaseHandler, playerView, getTrackById, getTrackByName)
    }

    @Test
    fun onConstruction_setsSelfOnView() {
        verify(playerView).setPresenter(playerPresenter)
    }

    @Test
    fun onLoadTrackById_successFromGetTrack_callsShowTrackOnView() {
        playerPresenter.loadTrackById(TRACK.id)
        verifyLoadTrackByIdSuccess()
    }

    @Test
    fun onLoadTrackById_failureFromGetTrack_callsFailedToLoadOnView() {
        playerPresenter.loadTrackById(TRACK.id)

        verifySetLoadingIndicator(true)

        verify(useCaseHandler).execute(eq(getTrackById), byIdRequestCaptor.capture(),
                byIdCallbackCaptor.capture())
        assertEquals(TRACK.id, byIdRequestCaptor.firstValue.id)
        byIdCallbackCaptor.firstValue.onError()

        verifySetLoadingIndicator(false)
        verify(playerView).showFailedToLoadTrack()
    }

    @Test
    fun onLoadTrackByName_successFromGetTrack_callsShowTrackOnView() {
        playerPresenter.loadTrackByName(TRACK.displayName)

        verifySetLoadingIndicator(true)

        verify(useCaseHandler).execute(eq(getTrackByName), byNameRequestCaptor.capture(),
                byNameCallbackCaptor.capture())
        assertEquals(TRACK.displayName, byNameRequestCaptor.firstValue.name)
        byNameCallbackCaptor.firstValue.onSuccess(byNameResponse)

        verifySetLoadingIndicator(false)
        verify(playerView).showTrack(TRACK)
    }

    @Test
    fun onLoadTrackByName_failureFromGetTrack_callsFailedToLoadOnView() {
        playerPresenter.loadTrackByName(TRACK.displayName)

        verifySetLoadingIndicator(true)

        verify(useCaseHandler).execute(eq(getTrackByName), byNameRequestCaptor.capture(),
                byNameCallbackCaptor.capture())
        assertEquals(TRACK.displayName, byNameRequestCaptor.firstValue.name)
        byNameCallbackCaptor.firstValue.onError()

        verifySetLoadingIndicator(false)
        verify(playerView).showFailedToLoadTrack()
    }

    @Test
    fun onStart_getsTrackIdFromView_successLoadsTrack() {
        whenever(playerView.getTrackId()).thenReturn(TRACK.id)
        playerPresenter.start()
        verify(playerView).getTrackId()

        verifyLoadTrackByIdSuccess()
    }

    @Test
    fun onStart_getsTrackFromView_nullReturned_ShowsFailedToLoadTrack() {
        whenever(playerView.getTrackId()).thenReturn(null)
        playerPresenter.start()
        verify(playerView).getTrackId()
        verify(playerView).showFailedToLoadTrack()
    }

    private fun verifyLoadTrackByIdSuccess() {
        verifySetLoadingIndicator(true)

        verify(useCaseHandler).execute(eq(getTrackById), byIdRequestCaptor.capture(),
                byIdCallbackCaptor.capture())
        assertEquals(TRACK.id, byIdRequestCaptor.firstValue.id)
        byIdCallbackCaptor.firstValue.onSuccess(byIdResponse)

        verifySetLoadingIndicator(false)
        verify(playerView).showTrack(TRACK)
    }

    private fun verifySetLoadingIndicator(active: Boolean) {
        viewInOrder.verify(playerView).setLoadingIndicator(active)
    }
}
