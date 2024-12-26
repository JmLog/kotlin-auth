package phone.book.auth.service.token

import org.springframework.stereotype.Service
import phone.book.auth.util.token.TokenUtil

@Service
class TokenService {
    /**
     * Jwt 엑세스 토큰 생성
     */
    fun generateAccessToken(email: String): String {
        return TokenUtil.generateAccessToken(email)
    }

    /**
     * Jwt 리프레시 토큰 생성
     */
    fun generateRefreshToken(email: String): String {
        return TokenUtil.generateRefreshToken(email)
    }

    /**
     * Jwt 토큰 검증
     */
    fun validateToken(token: String): String? {
        return TokenUtil.validateToken(token)
    }
}