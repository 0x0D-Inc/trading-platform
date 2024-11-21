package lab.home.tradingplatform.auth.adapter.`in`.web

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@ControllerAdvice
class WebGlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    suspend fun handleException(ex: Exception) : ServerResponse {
        return ServerResponse
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(mapOf(
                "success" to false,
                "message" to "An error occurred during registration",
                "error" to (ex.message ?: "Unknown error")
            ))
    }

    @ExceptionHandler(BadRequestException::class)
    suspend fun handleBadRequestException(ex: BadRequestException) : ServerResponse {
        return ServerResponse
            .badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(mapOf(
                    "success" to false,
                    "message" to "Invalid request",
                    "error" to ex.message
            ))
    }
}
