package lab.home.tradingplatform.auth.adapter.`in`.web

import io.kotest.core.spec.style.BehaviorSpec
import lab.home.tradingplatform.MySQLContainerConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.RouterFunction

@SpringBootTest
@Import(MySQLContainerConfiguration::class)
internal class AuthControllerTest(
    private val authRouter: RouterFunction<*>
) : BehaviorSpec({
    lateinit var webTestClient: WebTestClient

    beforeSpec {
        webTestClient = WebTestClient.bindToRouterFunction(authRouter).build()
    }

    Given("A web client for testing auth endpoints") {
        When("registering a new user with valid data") {
            val response = webTestClient.post()
                .uri("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                    {
                        "fullName": "raam",
                        "email": "raam@gmail.com",
                        "password": "12345678",
                        "mobile": "9087662350"
                    }
                """.trimIndent())
                .exchange()

            Then("the registration should be successful") {
                response.expectStatus().isCreated
                response.expectBody()
                    .jsonPath("$.success").isEqualTo(true)
                    .jsonPath("$.message").isEqualTo("User registered successfully")
            }
        }

        When("registering a user with invalid data") {
            val response = webTestClient.post()
                .uri("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                    {
                        "fullName": "raam",
                        "email": "invalid-email",
                        "password": "12345678",
                        "mobile": "9087662350"
                    }
                """.trimIndent())
                .exchange()

            Then("the registration should fail with a bad request") {
                response.expectStatus().isBadRequest
                response.expectBody()
                    .jsonPath("$.success").isEqualTo(false)
                    .jsonPath("$.message").isEqualTo("User registration failed")
            }
        }

        /*When("the server encounters an internal error during registration") {
            // This scenario might require mocking the UserRegisterUseCase to throw an exception
            // For demonstration, we'll use an invalid JSON to trigger an error
            val response = webTestClient.post()
                .uri("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("invalid json")
                .exchange()

            Then("the server should respond with an internal server error") {
                response.expectStatus().is5xxServerError
                response.expectBody()
                    .jsonPath("$.success").isEqualTo(false)
                    .jsonPath("$.message").isEqualTo("An error occurred during registration")
            }
        }*/
    }
})
