package lab.home.tradingplatform.auth.domain.exception

class AuthException(
    val errorCode: AuthErrorCode,
    override val message: String? = errorCode.message
) : RuntimeException(message)
