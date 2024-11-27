package lab.home.tradingplatform.auth.application.port.out

import lab.home.tradingplatform.auth.domain.User

// User Domain repository interface
// Bad Naming....
interface UserPort {
    suspend fun saveUser(user: User): User
}
