package lab.home.tradingplatform.common.r2dbc

import io.r2dbc.spi.ConnectionFactory
import lab.home.tradingplatform.auth.adapter.out.persistence.UserRoleReadConverter
import lab.home.tradingplatform.auth.adapter.out.persistence.UserRoleWriteConverter
import lab.home.tradingplatform.auth.adapter.out.persistence.VerificationTypeReadConverter
import lab.home.tradingplatform.auth.adapter.out.persistence.VerificationTypeWriteConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.dialect.DialectResolver
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories
@EnableR2dbcAuditing
class R2dbcConfig {
    @Bean
    fun r2dbcCustomConversions(connectionFactory: ConnectionFactory): R2dbcCustomConversions {
        val dialect = DialectResolver.getDialect(connectionFactory)
        val converters =
            dialect.converters + R2dbcCustomConversions.STORE_CONVERTERS + getCustomConverters()

        return R2dbcCustomConversions.of(dialect, converters)
    }

    fun getCustomConverters(): List<Any> =
        listOf(
            // common converters
            UuidWriteConverter(),
            UuidReadConverter(),
            // domain specific converters
            UserRoleWriteConverter(),
            UserRoleReadConverter(),
            VerificationTypeWriteConverter(),
            VerificationTypeReadConverter()
        )
}
