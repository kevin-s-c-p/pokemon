package mx.com.myapplication.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mx.com.myapplication.App
import mx.com.myapplication.viewstate.ViewState

abstract class BaseViewModel(): ViewModel() {
    private lateinit var _viewState: MutableStateFlow<ViewState>
    private lateinit var viewState: StateFlow<ViewState>

    protected fun initViewState(state: ViewState) {
        _viewState = MutableStateFlow(state)
        viewState = _viewState.asStateFlow()
    }

    protected fun updateViewState(newViewState: ViewState) {
        _viewState.update { newViewState }
    }

    protected fun <T> currentViewState(): T = _viewState.value as T

    fun <T> getState() = viewState as StateFlow<T>

    protected fun showToast(message: String) {
        Toast.makeText(App.appContext, message, Toast.LENGTH_SHORT).show()
    }
}