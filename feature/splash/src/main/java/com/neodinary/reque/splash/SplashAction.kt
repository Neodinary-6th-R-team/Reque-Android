package com.neodinary.reque.splash

sealed interface SplashAction {
    data object NavigateToLogin : SplashAction
}