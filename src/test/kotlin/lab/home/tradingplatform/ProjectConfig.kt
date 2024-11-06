package lab.home.tradingplatform

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

// Active Globally
object ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)
}