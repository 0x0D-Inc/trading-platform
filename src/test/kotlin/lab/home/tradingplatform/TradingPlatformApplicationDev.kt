package lab.home.tradingplatform

import org.springframework.boot.fromApplication

class TradingPlatformApplicationDev

// reference : https://docs.spring.io/spring-boot/reference/features/dev-services.html
// TODO: Using Testcontainers at Development Time -> spring-boot-docker-compose
fun main(args: Array<String>) {
    System.setProperty("spring.profiles.active", "local")
    fromApplication<TradingPlatformApplication>()
        .with(MySQLContainerConfiguration::class.java)
        .run(*args)
}
