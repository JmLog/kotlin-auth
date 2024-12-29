package phone.book.auth.service.auth

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import phone.book.auth.dto.user.UserDto
import phone.book.auth.entity.User

@SpringBootTest
class UserServiceTest {
    @Test
    fun signupTest() {
        val userDto = UserDto(
            name = "백지민",
            email = "wmlals0002@gmail.com",
            password = "B347060n1!",
            phone = "01012345678"
        )

        val user = User(
            name = userDto.name,
            email = userDto.email,
            password = userDto.password,
            phone = userDto.phone
        )
        println(user)
    }
}