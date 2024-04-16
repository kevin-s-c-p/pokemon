package mx.com.myapplication.ui.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.com.myapplication.repositories.user.UserRepository
import mx.com.myapplication.ui.splash.view.event.SplashViewEvent
import mx.com.myapplication.ui.splash.view.state.SplashViewState
import mx.com.myapplication.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {
    init {
        initViewState(SplashViewState())
    }

    fun onEvent(event: SplashViewEvent) {
        when(event) {
            SplashViewEvent.VerifyUser -> verifyUser()
        }
    }

    private fun verifyUser() {
        val state: SplashViewState = currentViewState()
        userRepository.verifyUser().onEach {
            delay(2000)
            updateViewState(state.copy(splashLoadedCompleted = true))
        }.launchIn(viewModelScope)
    }
}