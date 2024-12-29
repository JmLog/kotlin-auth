package phone.book.auth.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class UserDto(
    val name: String,

    @field:NotBlank(message = "이메일은 필수값입니다.")
    @field:Email(message = "이메일 형식이 아닙니다.")
    val email: String,

    @field:NotBlank(message = "비밀번호는 필수값입니다.")
    @field:Pattern(
        regexp = """^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,}$""",
        message = "비밀번호는 10자리 이상, 영문, 숫자, 특수문자 조합이어야 합니다."
    )
    val password: String,

    @field:NotBlank(message = "전화번호는 필수값입니다.")
    @field:Pattern(
        regexp = """^\d{11}$""",
        message = "전화번호는 11자리 숫자만 허용됩니다."
    )
    val phone: String
)