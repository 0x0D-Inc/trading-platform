package lab.home.tradingplatform.auth.adapter.out.persistence

import lab.home.tradingplatform.auth.application.port.out.UserPort
import lab.home.tradingplatform.auth.domain.User
import lab.home.tradingplatform.common.PersistenceAdapter

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userCoroutineRepository: UserCoroutineRepository,
) : UserPort {
    override suspend fun saveUser(user: User): User {
        val newUserEntity = user.toR2dbcEntity()
        val savedUserEntity = userCoroutineRepository.save(newUserEntity)
        return savedUserEntity.toUserEntity()
    }
}
