package lab.home.tradingplatform

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.common.runBlocking
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest
import lab.home.tradingplatform.auth.adapter.out.persistence.UserCoroutineRepository
import lab.home.tradingplatform.auth.adapter.out.persistence.UserR2dbcEntity
import lab.home.tradingplatform.auth.domain.UserRole
import lab.home.tradingplatform.auth.domain.VerificationType
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

private val logger = KotlinLogging.logger { }

@SpringBootTest
@Import(MySQLContainerConfiguration::class)
internal class TradingPlatformApplicationTest(
    private val sut: UserCoroutineRepository
) : FunSpec({
        lateinit var user: UserR2dbcEntity

        beforeTest {
            user =
                UserR2dbcEntity(
                    fullName = "Alice Apple",
                    email = "alice.apple@example.com",
                    userRole = UserRole.ADMIN,
                    verificationType = VerificationType.MOBILE
                )
            user =
                runBlocking {
                    sut.save(user)
                }
            logger.info { "Saved Entity : $user" }
        }

        test("update with modifying") {
            runTest {
                logger.info { "${user.id} will be updated" }
                val result = sut.updateWithModifying("Alice Google", user.id)
                result shouldNotBe null
            }
        }

        test("update without modifying") {
            runTest {
                logger.info { "${user.id} will be updated" }
                val result = sut.updateWithoutModifying("Alice Google", user.id)
                result shouldNotBe null
            }
        }

        test("find user by full name") {
            runTest {
                val result =
                    sut.findByFullName("Alice Apple").collect {
                        logger.info { "$it" }
                    }
                result shouldNotBe null
            }
        }

        test("read all records") {
            runTest {
                val result = sut.findAll().collect { logger.info { "$it" } }
                result shouldNotBe null
            }
        }
    })
