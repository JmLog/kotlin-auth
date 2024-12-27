package phone.book.auth.config

import io.github.cdimascio.dotenv.Dotenv
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class DotenvConfig {
    @PostConstruct
    fun loadEnv() {
        val dotenv = Dotenv.load()
        dotenv.entries().forEach { entry ->
            System.setProperty(entry.key, entry.value)
        }
    }
}