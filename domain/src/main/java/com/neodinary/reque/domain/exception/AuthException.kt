package com.neodinary.reque.domain.exception

sealed class AuthException : Exception{
    constructor() : super()
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    class InvalidAccessTokenException(message : String) : AuthException(message)
    class ExpiredAccessTokenException(message : String) : AuthException(message)
    class UnAuthorizationException(message : String) : AuthException(message)
    class AlreadyExistUserException(message : String) : AuthException(message)
}