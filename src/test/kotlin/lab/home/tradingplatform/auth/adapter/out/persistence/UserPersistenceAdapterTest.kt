package lab.home.tradingplatform.auth.adapter.out.persistence

import io.kotest.core.spec.style.BehaviorSpec
import lab.home.tradingplatform.MySQLContainerConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.r2dbc.core.DatabaseClient

@SpringBootTest
@Import(MySQLContainerConfiguration::class)
class UserPersistenceAdapterTest(
    private val userRepository: UserCoroutineRepository,
    private val databaseClient: DatabaseClient
) : BehaviorSpec({
     /*   beforeSpec {
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
        }

        Given("UserCoroutineRepository") {
            val user =
                UserR2dbcEntity(
                    fullName = "John Doe",
                    email = "john@example.com",
                    userRole = UserRole.CUSTOMER,
                    verificationType = VerificationType.EMAIL
                )

            When("saving a new user") {
                val savedUser = userRepository.save(user)

                Then("the user should be saved with an ID") {
                    savedUser.id shouldNotBe null
                    savedUser.fullName shouldBe "John Doe"
                    savedUser.email shouldBe "john@example.com"
                    savedUser.userRole shouldBe UserRole.CUSTOMER
                    savedUser.verificationType shouldBe VerificationType.EMAIL
                }
            }

            When("finding a user by ID") {
                val savedUser = userRepository.save(user)
                val foundUser = userRepository.findById(savedUser.id)

                Then("the user should be found") {
                    foundUser shouldNotBe null
                    foundUser?.fullName shouldBe "John Doe"
                }
            }

            When("finding users by full name") {
                userRepository.save(user)
                val users = userRepository.findByFullName("John Doe").toList()

                Then("the user should be found") {
                    users.size shouldBe 1
                    users.first().email shouldBe "john@example.com"
                }
            }

            When("updating a user with modifying query") {
                val savedUser = userRepository.save(user)
                val updatedRows = userRepository.updateWithModifying("Jane Doe", savedUser.id)

                Then("the user should be updated") {
                    updatedRows shouldBe 1
                    val updatedUser = userRepository.findById(savedUser.id)
                    updatedUser?.fullName shouldBe "Jane Doe"
                }
            }
        }*/
    })
