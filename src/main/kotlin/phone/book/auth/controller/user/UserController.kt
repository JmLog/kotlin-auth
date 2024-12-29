package phone.book.auth.controller.user

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import phone.book.auth.dto.ApiResponse
import phone.book.auth.dto.user.UserDto
import phone.book.auth.entity.User
import phone.book.auth.service.user.UserService

@RestController
@RequestMapping("/auth")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody userDto: UserDto): ApiResponse<User> {
        try {
            return ApiResponse(
                code = 200,
                message = "가입에 성공했습니다.",
                data = userService.signup(userDto = userDto)
            )
        } catch (e: IllegalArgumentException) {
            return ApiResponse(
                code = 400,
                message = e.message ?: "로그인 실패"
            )
        }
    }
}