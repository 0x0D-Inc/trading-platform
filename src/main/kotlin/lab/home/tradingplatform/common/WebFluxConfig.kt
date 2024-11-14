package lab.home.tradingplatform.common

import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
// @EnableWebFlux
class WebFluxConfig : WebFluxConfigurer {
    /*@Bean
    fun kotlinSerializationJson() = Json {
        ignoreUnknownKeys = true
    }

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().kotlinSerializationJsonEncoder(
            KotlinSerializationJsonEncoder(kotlinSerializationJson())
        )
        configurer.defaultCodecs().kotlinSerializationJsonDecoder(
            KotlinSerializationJsonDecoder(kotlinSerializationJson())
        )
    }*/

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().configureDefaultCodec { KotlinSerializationJsonHttpMessageConverter() }
        configurer.defaultCodecs().kotlinSerializationJsonEncoder(KotlinSerializationJsonEncoder())
        configurer.defaultCodecs().kotlinSerializationJsonDecoder(KotlinSerializationJsonDecoder())
   }
}
