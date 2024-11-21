package lab.home.tradingplatform.auth.application.port.`in`

interface UserRegisterUseCase {
    suspend fun registerUser(userRegisterCommand: UserRegisterCommand) : Boolean
}
