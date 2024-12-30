package phone.book.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import phone.book.auth.filter.JwtAuthenticationFilter
import phone.book.auth.repository.auth.UserRepository
import phone.book.auth.service.token.TokenService

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() } // 기본 로그아웃 동작 비활성화
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/",
                    "/login",
                    "/auth/signup"
                ).permitAll() // 인증 없이 허용
                    .anyRequest()
                    .authenticated() // 나머지 요청은 인증 필요
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun jwtAuthenticationFilter(
        tokenService: TokenService,
        userRepository: UserRepository
    ): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(tokenService, userRepository)
    }
}