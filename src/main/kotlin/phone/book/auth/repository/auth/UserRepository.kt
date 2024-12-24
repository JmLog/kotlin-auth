package phone.book.auth.repository.auth

import org.springframework.data.jpa.repository.JpaRepository
import phone.book.auth.entity.User

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}