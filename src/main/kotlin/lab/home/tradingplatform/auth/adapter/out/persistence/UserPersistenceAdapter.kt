package lab.home.tradingplatform.auth.adapter.out.persistence

import lab.home.tradingplatform.auth.application.port.out.UserPort
import lab.home.tradingplatform.auth.domain.User
import lab.home.tradingplatform.auth.domain.exception.AuthErrorCode
import lab.home.tradingplatform.auth.domain.exception.AuthException
import lab.home.tradingplatform.common.PersistenceAdapter
import org.springframework.dao.DataIntegrityViolationException

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userCoroutineRepository: UserCoroutineRepository
) : UserPort {
    override suspend fun saveUser(user: User): User {
        try {
            val newUserEntity = user.toR2dbcEntity()
            val savedUserEntity = userCoroutineRepository.save(newUserEntity)
            return savedUserEntity.toUserEntity()
        } catch (e : DataIntegrityViolationException) {
            if (e.message?.contains("email", ignoreCase = true) == true) {
                throw AuthException(AuthErrorCode.USER_EMAIL_CONFLICT)
            }
            throw e
        }
    }
}
