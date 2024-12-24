package phone.book.auth.service.token

import org.springframework.stereotype.Service
import phone.book.auth.util.token.TokenUtil

@Service
class TokenService {
    /**
     * Jwt 토큰 생성
     */
    fun generateToken(email: String): Map<String, String> {
        return mapOf(
            "accessToken" to TokenUtil.generateAccessToken(email),
            "refreshToken" to TokenUtil.generateRefreshToken(email)
        )
    }

    /**
     * Jwt 토큰 검증
     */
    fun validateToken(token: String): String? {
        return TokenUtil.validateToken(token)
    }
}