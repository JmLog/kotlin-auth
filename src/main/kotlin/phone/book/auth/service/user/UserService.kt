package phone.book.auth.service.user

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import phone.book.auth.dto.user.UserDto
import phone.book.auth.entity.User
import phone.book.auth.repository.auth.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signup(userDto: UserDto): User {
        try {
            val password = passwordEncoder.encode(userDto.password)
            val user = User(
                name = userDto.name,
                email = userDto.email,
                password = password,
                phone = userDto.phone
            )

            return userRepository.save(user)
        } catch (e: DataIntegrityViolationException) {
            throw IllegalArgumentException("가입한 이력이 있는지 확인해주세요!")
        }
    }
}