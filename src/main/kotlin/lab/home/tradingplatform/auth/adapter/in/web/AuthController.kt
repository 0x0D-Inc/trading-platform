package lab.home.tradingplatform.auth.adapter.`in`.web

import lab.home.tradingplatform.auth.application.port.`in`.UserRegisterCommand
import lab.home.tradingplatform.auth.application.port.`in`.UserRegisterUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AuthController(
    private val exceptionHandler: WebGlobalExceptionHandler
) {
    @Bean
    fun authRouter(authHandler: AuthHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "/auth".nest {
                POST("/signup", authHandler::register)
            }
        }
//        onError<Exception> { ex, _ -> exceptionHandler.handleException(ex) }
//        onError<BadRequestException> { ex, _ -> exceptionHandler.handleException(ex) }
    }
}


@Component
class AuthHandler(
    private val userRegisterUseCase: UserRegisterUseCase
) {
    suspend fun register(serverRequest: ServerRequest) : ServerResponse {
        return try {
            val command = serverRequest.awaitBody<UserRegisterCommand>()
            val result = userRegisterUseCase.registerUser(command)

            if (result) {
                ServerResponse.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(mapOf("success" to true, "message" to "User registered successfully"))
            } else {
                ServerResponse.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValueAndAwait(mapOf("success" to false, "message" to "User registration failed"))
            }
        } catch (e: Exception) {
            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(mapOf("success" to false, "message" to "An error occurred during registration")
                )
        }
    }
}
