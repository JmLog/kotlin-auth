package phone.book.auth.service.auth

import jakarta.validation.constraints.Email
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import phone.book.auth.dto.auth.LoginDto
import phone.book.auth.entity.User
import phone.book.auth.repository.auth.UserRepository
import phone.book.auth.service.token.TokenService

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService
) {
    /**
     * 로그인
     */
    fun login(loginResult: LoginDto): User {
        // db 에서 사용자 조회
        val user = userRepository.findByEmail(loginResult.email)
            ?: throw IllegalArgumentException("가입되지 않은 이메일입니다.")

        // 비밀번호 일치 여부 확인
        require(passwordEncoder.matches(loginResult.password, user.password)) {
            "비밀번호가 일치하지 않습니다."
        }

        val accessToken = user.accessToken
        val refreshToken = user.refreshToken

        // 토큰의 상태를 보고 갱신 여부 결정
        when (accessToken) {
            null -> {
                this.saveToken(user, accessToken = true, refreshToken = true)
            }
            else -> {
                // access token 검증
                val validateToken = tokenService.validateToken(accessToken)
                if (validateToken == null) {
                    // refresh token 검증
                    refreshToken?.let {
                        if (tokenService.validateToken(it) == null) {
                            // refreshToken 은 트리거로 사용하며 만료시 둘 다 재갱신
                            this.saveToken(user = user, accessToken = true, refreshToken = true)
                        } else {
                            // accessToken 만 갱신
                            this.saveToken(user = user, accessToken = true)
                        }
                    }
                }
            }
        }

        return user
    }

    /**
     * 로그아웃
     */
    fun logout(email: String) {
        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("가입되지 않은 이메일입니다.")
        println("user ${user}")
        user.apply {
            this.accessToken = null
            this.refreshToken = null
        }

        userRepository.save(user)
    }

    /**
     * 토큰 저장
     */
    fun saveToken(user: User, accessToken: Boolean = false, refreshToken: Boolean = false) {
        user.apply {
            if (accessToken) this.accessToken = tokenService.generateAccessToken(user.email)
            if (refreshToken) this.accessToken = tokenService.generateRefreshToken(user.email)
        }
        userRepository.save(user)
    }
}