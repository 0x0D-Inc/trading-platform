package lab.home.tradingplatform.auth.adapter.`in`.web

import lab.home.tradingplatform.auth.application.port.out.AuthExceptionHandler
import lab.home.tradingplatform.auth.application.port.out.ErrorResponse
import lab.home.tradingplatform.auth.domain.exception.AuthException
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.time.Instant

/*class AuthControllerExceptionHandler : AuthExceptionHandler {
    override suspend fun handleAuthException(e: AuthException): Map<String, Any> {
        TODO("Not yet implemented")
    }
}*/

@ControllerAdvice
class AuthControllerExceptionHandler : AuthExceptionHandler {
    override suspend fun handleAuthException(e: AuthException): ErrorResponse {
        return ErrorResponse(
            status = e.errorCode.status,
            code = e.errorCode.code,
            message = e.message ?: e.errorCode.message
        )
    }

    @ExceptionHandler(AuthException::class)
    suspend fun authExceptionHandler(ex: AuthException) : ServerResponse {
        val errorResponse = handleAuthException(ex)
        return ServerResponse
            .status(ex.errorCode.status)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(
                mapOf(
                    "status" to errorResponse.status,
                    "error" to errorResponse.code,
                    "message" to errorResponse.message,
                    "timestamp" to Instant.now().toString()
                )
            )
    }
}
