package lab.home.tradingplatform.user.adapter.`in`.web

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import lab.home.tradingplatform.user.application.port.`in`.UserAuthenticationUseCase
import lab.home.tradingplatform.user.application.port.`in`.UserRegistrationUseCase
import lab.home.tradingplatform.user.domain.User
import org.springframework.http.MediaType
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@RestController
@RequestMapping("/api/auth")
class AuthenticationController (
    private val authenticationUseCase: UserAuthenticationUseCase,
    private val userRegistrationUseCase: UserRegistrationUseCase
) {
    /*@PostMapping("/login")
    suspend fun login(@RequestBody request: ServerRequest) : ServerResponse {
        val command = UserAuthenticationCommand

        return mono {
            val token = authenticationUseCase.login(command)
            ServerResponse.ok().bodyValueAndAwait(LoginResponse(token))
        }.awaitSingle()
    }*/
    @PostMapping("/login", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    // @Transactional
    suspend fun login(@RequestBody user: User, @RequestParam(required=false) delay: Long?) = coroutineScope {
        val command = UserAuthenticationCommand()
    }
}