package lab.home.tradingplatform

import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

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
        println("\t📁 API document : http://localhost:${sp.port}/swagger-ui.html \n")
        println("\t🐑 Swagger Yaml : http://localhost:${sp.port}/docs.yaml\n")

        println("\t🚀 start data initialization ...\n")
        runBlocking {
            /*val deleted = postRepository.deleteAll()
            println("\t[$deleted] posts removed ")
            postRepository.init()*/
        }
        println("\n\t OK: done data initialization... 🐲\n")
    }
}
