package bcnc.albums.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AppConfig {

    @Value("\${spring.application.name}")
    private lateinit var url :String

    @Bean
    fun webClientPlaceHolder(): WebClient {
        return WebClient.builder()
            .baseUrl(url)
            .build()
    }
}