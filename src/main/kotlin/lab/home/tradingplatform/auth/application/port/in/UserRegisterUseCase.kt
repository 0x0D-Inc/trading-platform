package lab.home.tradingplatform.auth.application.port.`in`

import lab.home.tradingplatform.auth.application.service.RegisterUserResult

interface UserRegisterUseCase {
    suspend fun registerUser(userRegisterCommand: UserRegisterCommand): RegisterUserResult
}
