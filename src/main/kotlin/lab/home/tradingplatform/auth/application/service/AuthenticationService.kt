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
) : UserRegisterUseCase {
    override suspend fun registerUser(userRegisterCommand: UserRegisterCommand): RegisterUserResult {
        val newUserId = UserId(UUIDv7.randomUUID())

        val user =
            User(
                fullName = userRegisterCommand.fullName,
                email = userRegisterCommand.email,
                userRole = UserRole.CUSTOMER,
                TwoFactorAuth(), // TODO: mobile <-> sendTo data mapping???
                isLocked = false,
                isEnabled = true,
                id = newUserId
            )

        /* TODO: Exception 을 어느 단계에서 던질까? */
        val savedEntity = userPersistenceAdapter.saveUser(user)

        return savedEntity.toRegisterUserResult()

    }
}
