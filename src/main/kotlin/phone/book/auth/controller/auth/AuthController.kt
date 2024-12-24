package phone.book.auth.controller.auth

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import phone.book.auth.dto.ApiResponse
import phone.book.auth.dto.auth.UserRequest
import phone.book.auth.entity.User
import phone.book.auth.service.auth.AuthService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody userRequest: UserRequest): ApiResponse<User> {
        return ApiResponse(
            code = 200,
            message = "가입에 성공했습니다.",
            data = authService.signup(userRequest)
        )
    }
}