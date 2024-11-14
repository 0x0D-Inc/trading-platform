package lab.home.tradingplatform

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class TradingPlatformApplication

fun main(args: Array<String>) {
    runApplication<TradingPlatformApplication>(*args)
}

@Component
class Initializer(
    private val sp: ServerProperties
) {
    @EventListener(value = [ApplicationReadyEvent::class])
    fun init() {
        /*runBlocking {
            withLoggingContext("kotlin" to "the moon") {
                launch(MDCContext()) {
                    logger.info { "..." }
                }
            }
        }*/

        // lazily evaluated
        logger.info { "\t📁 API document : http://localhost:${sp.port}/swagger-ui.html \n" }
        logger.info { "\t🐑 Swagger Yaml : http://localhost:${sp.port}/docs.yaml\n" }

        logger.info { "\t🚀 start data initialization ...\n" }
        runBlocking {
            /*val deleted = postRepository.deleteAll()
            logger.info("\t[$deleted] posts removed ")
            postRepository.init()*/
        }
        logger.info { "\n\t OK: done data initialization... 🐲\n" }
    }
}
