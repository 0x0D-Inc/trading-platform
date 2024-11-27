package lab.home.tradingplatform.auth.adapter.`in`.web

import io.kotest.core.spec.style.BehaviorSpec
import lab.home.tradingplatform.MySQLContainerConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

@SpringBootTest
@Import(MySQLContainerConfiguration::class)
internal class AuthControllerTest(
//    private val authRouter: RouterFunction<*>
    private val authRouter: RouterFunction<ServerResponse>
) : BehaviorSpec({
        lateinit var webTestClient: WebTestClient

        beforeSpec {
            webTestClient = WebTestClient.bindToRouterFunction(authRouter).build()
        }

        Given("auth endpoint 테스트를 위한 웹 클라이언트가 주어졌을 때") {
            When("유효한 데이터를 이용해서 새로운 사용자를 등록 하면") {
                val response =
                    webTestClient
                        .post()
                        .uri("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(
                            """
                            {
                                "fullName": "raam",
                                "email": "raam@gmail.com",
                                "password": "12345678",
                                "mobile": "9087662350"
                            }
                            """.trimIndent()
                        ).exchange()

                Then("새로운 사용자 등록이 성공한다") {
                    response.expectStatus().isCreated
                    response
                        .expectBody()
                        .jsonPath("$.success")
                        .isEqualTo(true)
                        .jsonPath("$.message")
                        .isEqualTo("User registered successfully")
                }
            }

            When("유효하지 않은 데이터를 이용해서 새로운 사용자를 등록 하면") {
                val response =
                    webTestClient
                        .post()
                        .uri("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(
                            """
                            {
                                "fullName": "raam",
                                "email": "invalid-email",
                                "password": "12345678",
                                "mobile": "9087662350"
                            }
                            """.trimIndent()
                        ).exchange()

                Then("새로운 사용자 등록이 실패한다") {
                    response.expectStatus().isBadRequest
                    response
                        .expectBody()
                        .jsonPath("$.success")
                        .isEqualTo(false)
                        .jsonPath("$.message")
                        .isEqualTo("User registration failed")
                }
            }

        /*When("새로운 사용자 등록 중에 internal error 가 발생하면") {
            // This scenario might require mocking the UserRegisterUseCase to throw an exception
            // For demonstration, we'll use an invalid JSON to trigger an error
            val response = webTestClient.post()
                .uri("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("invalid json")
                .exchange()

            Then("서버는 internal server error 로 응답한다") {
                response.expectStatus().is5xxServerError
                response.expectBody()
                    .jsonPath("$.success").isEqualTo(false)
                    .jsonPath("$.message").isEqualTo("An error occurred during registration")
            }
        }*/
        }
    })
