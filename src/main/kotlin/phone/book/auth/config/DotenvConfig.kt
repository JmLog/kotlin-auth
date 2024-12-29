package phone.book.auth.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Configuration

@Configuration
class DotenvConfig {
    companion object {
        init {
            val dotenv = Dotenv.load()
            dotenv.entries().forEach { entry ->
                System.setProperty(entry.key, entry.value)
            }
        }
    }
}