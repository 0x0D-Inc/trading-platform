package lab.home.tradingplatform.auth.application.port.out

import lab.home.tradingplatform.auth.domain.exception.AuthErrorCode

data class ErrorResponse (
    val status: Int,
    val code: String,
    val message: String
)
