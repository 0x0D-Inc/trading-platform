package lab.home.tradingplatform

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class MySQLContainerConfiguration {
    @Bean
    @ServiceConnection
    fun mySQLContainer() : MySQLContainer<*> {
        return MySQLContainer(DockerImageName.parse("mysql:8.0.36"))
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("schema.sql")
//            .withReuse(true)
    }
}