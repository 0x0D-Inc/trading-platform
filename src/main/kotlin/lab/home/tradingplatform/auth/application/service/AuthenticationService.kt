package lab.home.tradingplatform.auth.application.service

import lab.home.tradingplatform.auth.adapter.out.persistence.UserPersistenceAdapter
import lab.home.tradingplatform.auth.application.port.`in`.UserRegisterCommand
import lab.home.tradingplatform.auth.application.port.`in`.UserRegisterUseCase
import lab.home.tradingplatform.auth.domain.TwoFactorAuth
import lab.home.tradingplatform.auth.domain.User
import lab.home.tradingplatform.auth.domain.UserId
import lab.home.tradingplatform.auth.domain.UserRole
import lab.home.tradingplatform.common.UUIDv7
import lab.home.tradingplatform.common.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class AuthenticationService(
    private val userPersistenceAdapter: UserPersistenceAdapter
): UserRegisterUseCase {
    override suspend fun registerUser(userRegisterCommand: UserRegisterCommand): Boolean {
        /* Domain 으로 가지고 이것 저것 Domain Service 호출 */
        val newUserId = UserId(UUIDv7.randomUUID())

        val user = User(
            fullName = userRegisterCommand.fullName,
            email = userRegisterCommand.email,
            userRole = UserRole.CUSTOMER,
            TwoFactorAuth(),    // TODO: mobile <-> sendTo data mapping???
            isLocked = false,
            isEnabled = true,
            id = newUserId
        )

        val savedEntity = userPersistenceAdapter.saveUser(user)
        /* validate */
        return savedEntity.id == newUserId
    }
}
