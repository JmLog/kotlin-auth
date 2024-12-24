package phone.book.auth.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import phone.book.auth.dto.ApiResponse

@ControllerAdvice
class GlobalExceptionHandler {
    // 유효성 검사 실패만 처리
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<List<Map<String, String>>>> {
        // 필드별 에러 메시지 추출
        val errors = e.bindingResult.fieldErrors.map {
            mapOf(
                "field" to it.field,
                "message" to (it.defaultMessage ?: "Invalid value")
            )
        }

        // ApiResponse 생성 및 반환
        val response = ApiResponse(
            code = 400,
            message = "유효성 검사가 실패했습니다.",
            data = errors
        )

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}