package lab.home.tradingplatform.auth.adapter.out.persistence

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.flow.toList
import lab.home.tradingplatform.MySQLContainerConfiguration
import lab.home.tradingplatform.auth.domain.UserId
import lab.home.tradingplatform.auth.domain.UserRole
import lab.home.tradingplatform.auth.domain.VerificationType
import lab.home.tradingplatform.common.UUIDv7
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(MySQLContainerConfiguration::class)
class UserCoroutineRepositoryTest(
    private val userRepository: UserCoroutineRepository
//    private val databaseClient: DatabaseClient
) : BehaviorSpec({
    /*beforeSpec {
        databaseClient
            .sql("DROP TABLE IF EXISTS users")
            .fetch()
            .rowsUpdated()
            .awaitSingle()
        databaseClient
            .sql(
                """
                    CREATE TABLE IF NOT EXISTS users (
                        id BINARY(16) NOT NULL PRIMARY KEY,
                        full_name VARCHAR(255),
                        email VARCHAR(255),
                        user_role VARCHAR(255) NOT NULL,
                        verification_type VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                        updated_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
                        )
                    """.trimIndent()
            ).fetch()
            .rowsUpdated()
            .awaitSingle()
    }

    afterSpec {
        databaseClient
            .sql("DROP TABLE IF EXISTS users")
            .fetch()
            .rowsUpdated()
            .awaitSingle()
    }*/

        Given("새로운 UserR2dbcEntity 가 주어졌을때") {
        /*SEND
        {
            "fullName" : "asdf",
            "email": "asdfasf@gasdg.com",
            "password": "12125152152",
            "mobile": "15135151315135"
        }
        RECEIVE
        {
            "id" : "a1521251-251251-25125125125",
            "fullName: null,
            "twoFactorAuth": {
                "sendTo": null,
                "enabled": false
            }
            "role": "ROLE_CUSTOMER"
        }*/
            val userId = UserId(UUIDv7.randomUUID())
            val newUser =
                UserR2dbcEntity(
                    fullName = "John Doe",
                    email = "john@example.com",
                    userRole = UserRole.CUSTOMER,
                    twoFactorAuthIsEnabled = false,
                    twoFactorAuthSendTo = VerificationType.NONE,
                    id = userId.value
                /*  optional parameters
                isLocked,
                isEnabled,
                createdAt,
                updatedAt
                 */
                )

            When("새로운 UserR2dbcEntity 를 저장 하면") {
                val savedUser = userRepository.save(newUser)

                Then("저장된 UserR2dbcEntity 가 반환된다") {
                    savedUser.fullName shouldBe newUser.fullName
                    savedUser.email shouldBe newUser.email
                    savedUser.userRole shouldBe newUser.userRole
                    savedUser.twoFactorAuthIsEnabled shouldBe newUser.twoFactorAuthIsEnabled
                    savedUser.twoFactorAuthSendTo shouldBe newUser.twoFactorAuthSendTo
                    savedUser.id shouldBe newUser.id
                    savedUser.isLocked shouldBe false
                    savedUser.isEnabled shouldBe true
                    savedUser.createdAt shouldNotBe null
                    savedUser.updatedAt shouldNotBe null
                }
            }

            When("저장된 UserR2dbcEntity 의 ID 를 이용해서 User 를 찾으면") {
                val savedUser = userRepository.save(newUser)
                val foundUser = userRepository.findById(savedUser.id)

                Then("ID 에 해당하는 사용자가 반환 된다") {
                    foundUser shouldNotBe null
                    foundUser?.let {
                        it.fullName shouldBe savedUser.fullName
                        it.email shouldBe savedUser.email
                        it.userRole shouldBe savedUser.userRole
                        it.twoFactorAuthIsEnabled shouldBe savedUser.twoFactorAuthIsEnabled
                        it.twoFactorAuthSendTo shouldBe savedUser.twoFactorAuthSendTo
                        it.id shouldBe savedUser.id
                        it.isLocked shouldBe false
                        it.isEnabled shouldBe true
                        it.createdAt shouldNotBe null
                        it.updatedAt shouldNotBe null
                    }
                }
            }

            // TODO: 다른 테스트 케이스에 의존 -> 수정할것
            When("fullName 을 이용해서 User 를 찾으면") {
                val foundUser = userRepository.findByFullName(newUser.fullName).toList()

                Then("User 의 리스트가 반환된다") {
                    foundUser.size shouldBe 2
                }
            }

            When("modifying query 를 이용해서 User 의 fullName 을 변경하면") {
                val updatedFullName = "Alice Jane"
                val savedUser = userRepository.save(newUser)
                val updatedRows = userRepository.updateWithModifying(updatedFullName, savedUser.id)

                Then("User 가 업데이트 된다") {
                    val updatedUser = userRepository.findById(savedUser.id)
                    updatedUser shouldNotBe null
                    updatedUser?.let {
                        it.fullName shouldBe updatedFullName
                    }
                }
            }
        }
    })
