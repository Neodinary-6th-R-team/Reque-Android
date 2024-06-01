package com.neodinary.reque.splash

import androidx.lifecycle.viewModelScope
import com.neodinary.reque.common.ui.base.BaseViewModel
import com.neodinary.reque.domain.usecase.GetAccessTokenUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : BaseViewModel(){

    private val _action : MutableSharedFlow<SplashAction> = MutableSharedFlow()
    val action : SharedFlow<SplashAction> get() = _action.asSharedFlow()

    // 로그인을 한 적이 있는지
    fun checkFirst(){
        viewModelScope.launch {
            runCatching {
                getAccessTokenUseCase()
            }.onSuccess { accessToken ->
                // 비어있지 않고 null이 아니면
                if(accessToken.isNotEmpty()) {

                } else {
                    _action.emit(SplashAction.NavigateToLogin)
                }
            }.onFailure {
                _action.emit(SplashAction.NavigateToLogin)
            }
        }
    }

    // 로그인을 한 적이 없는지
}