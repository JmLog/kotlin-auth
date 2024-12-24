package phone.book.auth.service.auth

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import phone.book.auth.dto.auth.UserRequest
import phone.book.auth.entity.User

@SpringBootTest
class AuthServiceTest {
    @Test
    fun signupTest() {
        val userRequest = UserRequest(
            name = "백지민",
            email = "wmlals0002@gmail.com",
            password = "B347060n1!",
            phone = "01012345678"
        )

        val user = User(
            name = userRequest.name,
            email = userRequest.email,
            password = userRequest.password,
            phone = userRequest.phone
        )
        println(user)
    }
}