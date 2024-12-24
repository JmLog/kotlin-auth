package phone.book.auth.service.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import phone.book.auth.dto.user.LoginRequest
import phone.book.auth.entity.User
import phone.book.auth.repository.auth.UserRepository
import phone.book.auth.service.token.TokenService

@Service
class LoginService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService
) {
    fun login(loginResult: LoginRequest): User {
        // db 에서 사용자 조회
        val user = userRepository.findByEmail(loginResult.email)
            ?: throw IllegalArgumentException("가입되지 않은 이메일입니다.")

        // 비밀번호 일치 여부 확인
        if (passwordEncoder.matches(loginResult.password, user.password).not()) {
            throw IllegalArgumentException("잘못된 비밀번호입니다.")
        }

        // 정보가 모두 일치하면 토큰 생성
        // TODO 토큰 검증 후 없으면 리프레시 토큰으로 재발급
        // TODO 리프레시 토큰 만료 시간이 지나면 로그아웃 처리
        val token = tokenService.generateToken(user.email)
        user.accessToken = token["accessToken"]
        user.refreshToken = token["refreshToken"]
        userRepository.save(user)

        // User 정보 반환
        return user
    }
}