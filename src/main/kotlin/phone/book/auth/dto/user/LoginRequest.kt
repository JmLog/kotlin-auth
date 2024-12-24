package phone.book.auth.dto.user

data class LoginRequest(
    val email: String,
    val password: String
)