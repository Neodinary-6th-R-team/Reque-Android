package com.neodinary.reque.domain.usecase

import com.neodinary.reque.domain.repository.AuthRepository

class GetAccessTokenUseCase constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke() :String {
        return authRepository.getAccessToken()
    }
}