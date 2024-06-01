package com.neodinary.reque.domain.usecase

import com.neodinary.reque.domain.repository.AuthRepository
import javax.inject.Inject

class SetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(accessToken : String) = authRepository.setAccessToken(accessToken)
}