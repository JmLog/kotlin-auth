package phone.book.auth.dto

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)