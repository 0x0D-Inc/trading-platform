package lab.home.tradingplatform.user.application.port.`in`

interface UserAuthenticationUseCase {
    fun login(username: String, password: String): String
}