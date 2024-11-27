package lab.home.tradingplatform.common.webflux

import io.github.oshai.kotlinlogging.KotlinLogging
import lab.home.tradingplatform.common.exception.CommonErrorCode
import lab.home.tradingplatform.common.exception.CommonException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.time.Instant

private val logger = KotlinLogging.logger {}

@ControllerAdvice
class GlobalExceptionHandler {

    /* XXX: BAD */
    /*@ExceptionHandler(Exception::class)
    suspend fun handler(ex: Exception) : ServerResponse {
        return when (ex) {
            is BadRequestException -> handleBadRequestException(ex)
            else -> handleGenericException(ex)
        }
    }*/

    @ExceptionHandler(Exception::class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    suspend fun handleUnknownException(ex: Exception) : ServerResponse = ServerResponse
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .also {
                logger.error { ex.message }
            }.bodyValueAndAwait(
                mapOf(
                    "status" to CommonErrorCode.INTERNAL_SERVER_ERROR.status,
                    "error" to CommonErrorCode.INTERNAL_SERVER_ERROR.code,
                    "message" to CommonErrorCode.INTERNAL_SERVER_ERROR.message,
                    "timestamp" to Instant.now().toString()
                )
            )

    // NOTE: 이게 필요하나?
    @ExceptionHandler(CommonException::class)
    suspend fun handleCommonException(ex: CommonException) : ServerResponse =
        ServerResponse
            .status(ex.errorCode.status)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(
                mapOf(
                    "status" to ex.errorCode.status,
                    "error" to ex.errorCode.code,
                    "message" to ex.errorCode.message,
                    "timestamp" to Instant.now().toString()
                )
            )
}
