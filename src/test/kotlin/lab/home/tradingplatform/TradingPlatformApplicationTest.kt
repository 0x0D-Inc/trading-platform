package lab.home.tradingplatform

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

private val logger = KotlinLogging.logger { }

@SpringBootTest
@Import(MySQLContainerConfiguration::class)
internal class TradingPlatformApplicationTest :
    BehaviorSpec({
    })
