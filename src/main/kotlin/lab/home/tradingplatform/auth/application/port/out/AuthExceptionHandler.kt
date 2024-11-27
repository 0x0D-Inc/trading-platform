package lab.home.tradingplatform.auth.application.port.out

import lab.home.tradingplatform.auth.domain.exception.AuthException

interface AuthExceptionHandler {
    suspend fun handleAuthException(e: AuthException) : ErrorResponse
}
