package com.neodinary.reque.domain.usecase

import com.neodinary.reque.domain.repository.AuthRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke() :String {
        return authRepository.getAccessToken()
    }
}