package lab.home.tradingplatform.user.application.port.`in`

import lab.home.tradingplatform.user.domain.User

interface UserRegistrationUseCase {
    fun registerUser(user: User): User
}