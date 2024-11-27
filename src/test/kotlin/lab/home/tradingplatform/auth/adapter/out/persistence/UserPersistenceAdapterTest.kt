package lab.home.tradingplatform.auth.adapter.out.persistence

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import lab.home.tradingplatform.auth.domain.TwoFactorAuth
import lab.home.tradingplatform.auth.domain.User
import lab.home.tradingplatform.auth.domain.UserId
import lab.home.tradingplatform.auth.domain.UserRole
import lab.home.tradingplatform.common.UUIDv7
import java.time.Instant

class UserPersistenceAdapterTest :
    BehaviorSpec({
        val userCoroutineRepository = mockk<UserCoroutineRepository>()
        val userPersistenceAdapter = UserPersistenceAdapter(userCoroutineRepository)

        Given("사용자 저장 요청이 주어졌을때") {
            val userId = UserId(UUIDv7.randomUUID())
            val user =
                User(
                    "John Doe",
                    "john@example.com",
                    UserRole.ADMIN,
                    TwoFactorAuth(),
                    false,
                    true,
                    Instant.now(),
                    Instant.now(),
                    userId
                )
            val userR2dbcEntity = user.toR2dbcEntity()
            val savedUserR2dbcEntity = userR2dbcEntity
            coEvery {
                userCoroutineRepository.save(any<UserR2dbcEntity>())
            } returns savedUserR2dbcEntity

            When("saveUser 메소드를 호출 하면") {
                val result = userPersistenceAdapter.saveUser(user)
                Then("저장된 사용자 정보가 반환된다") {
                    result.id?.value shouldBe savedUserR2dbcEntity.id
                    result.fullName shouldBe savedUserR2dbcEntity.fullName
                    result.email shouldBe savedUserR2dbcEntity.email
                }
            }
        }
    })
