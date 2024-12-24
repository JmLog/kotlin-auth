package phone.book.auth.service.auth

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import phone.book.auth.dto.auth.UserRequest
import phone.book.auth.entity.User
import phone.book.auth.repository.auth.UserRepository

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    /**
     * 회원가입
     */
    fun signup(userRequest: UserRequest): User {
        val password = passwordEncoder.encode(userRequest.password)
        val user = User(
            name = userRequest.name,
            email = userRequest.email,
            password = password,
            phone = userRequest.phone
        )

        return userRepository.save(user)
    }
}