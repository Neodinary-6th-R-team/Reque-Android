package com.neodinary.reque.login

import com.neodinary.reque.common.ui.base.BaseViewModel
import com.neodinary.reque.domain.usecase.JoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
): BaseViewModel() {
    private val _action : MutableSharedFlow<LoginAction> = MutableSharedFlow()
    val action : SharedFlow<LoginAction> get() = _action.asSharedFlow()


}