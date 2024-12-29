package phone.book.auth.dto.auth

import jakarta.validation.constraints.Email

data class LogoutDto(
    val email: String
)