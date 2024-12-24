package phone.book.auth.controller.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import phone.book.auth.dto.ApiResponse
import phone.book.auth.dto.user.LoginRequest
import phone.book.auth.entity.User
import phone.book.auth.service.user.LoginService

@RestController
class UserController(
    private val loginService: LoginService
) {

    @GetMapping("/")
    fun home(): String {
        return "Hello, World!"
    }


    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ApiResponse<User> {
        try {
            val user = loginService.login(loginRequest)
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
}