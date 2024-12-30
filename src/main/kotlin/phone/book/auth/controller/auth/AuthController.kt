package phone.book.auth.controller.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import phone.book.auth.dto.ApiResponse
import phone.book.auth.dto.auth.LoginDto
import phone.book.auth.dto.auth.LogoutDto
import phone.book.auth.entity.User
import phone.book.auth.service.auth.AuthService

@RestController
class AuthController(
    private val authService: AuthService
) {
    @GetMapping("/")
    fun home(): String {
        val auth = SecurityContextHolder.getContext().authentication
        println(auth)
        return "Hello, World!"
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ApiResponse<User> {
        try {
            val user = authService.login(loginResult = loginDto)
            return ApiResponse(
                code = 200,
                message = "로그인 성공",
                data = user
            )
        } catch (e: IllegalArgumentException) {
            return ApiResponse(
                code = 400,
                message = e.message ?: "로그인 실패"
            )
        }
    }

    @PostMapping("/logout")
    fun logout(@RequestBody logoutDto: LogoutDto): ApiResponse<Any> {
        try {
            authService.logout(email = logoutDto.email)
            return ApiResponse(
                code = 200,
                message = "로그아웃 성공",
                data = null
            )
        } catch (e: IllegalArgumentException) {
            return ApiResponse(
                code = 400,
                message = e.message ?: "로그아웃 실패"
            )
        }
    }
}