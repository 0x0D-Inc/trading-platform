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
class AuthController {
    @Bean
    fun authRouter(authHandler: AuthHandler) =
        coRouter {
            accept(MediaType.APPLICATION_JSON).nest {
                "/auth".nest {
                    POST("/signup", authHandler::register)
                }
            }
        }
}

@Component
class AuthHandler(
    private val userRegisterUseCase: UserRegisterUseCase
) {
    suspend fun register(serverRequest: ServerRequest): ServerResponse {
        val command = serverRequest.awaitBody<UserRegisterCommand>()
        val registerUserResult = userRegisterUseCase.registerUser(command)
        return ServerResponse
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(registerUserResult)
    }
}
