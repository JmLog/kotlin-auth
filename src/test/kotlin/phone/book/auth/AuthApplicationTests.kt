package phone.book.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class AuthApplicationTests {

    @Test
    fun contextLoads() {
        val secretKey = Keys.hmacShaKeyFor("f6BOAGB0uwxh7QusW5Vee81oPZVzCYTyAXcyH0nnX3U=".toByteArray())
        val token = Jwts.builder()
            .setSubject("wmlals0002@gmail.com")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 7일
            .signWith(secretKey)
            .compact()

        println(token)
    }

}
