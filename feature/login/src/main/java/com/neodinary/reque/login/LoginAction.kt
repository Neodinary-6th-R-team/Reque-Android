package com.neodinary.reque.login

sealed interface LoginAction {
    data object DoKakaoLogin : LoginAction
}