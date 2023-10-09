package ru.sterkhovav.phlogiston.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import ru.sterkhovav.phlogiston.controllers.AUTH_BASE_API
import ru.sterkhovav.phlogiston.controllers.BASE_API
import ru.sterkhovav.phlogiston.controllers.ONE_PHASE_PATH
import ru.sterkhovav.phlogiston.security.CustomAuthenticationFailureHandler
import ru.sterkhovav.phlogiston.security.CustomAuthenticationSuccessHandler
import ru.sterkhovav.phlogiston.service.EmailServiceImpl
import ru.sterkhovav.phlogiston.service.UserDetailsServiceImpl
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        private val userDetailsServiceImpl: UserDetailsServiceImpl,
        private val emailServiceImpl: EmailServiceImpl
) {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceImpl)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        // CSRF
        http.csrf().disable() //TODO включить

        // Authorize Requests
        http.authorizeRequests()
                .antMatchers("/js/**", "$BASE_API/")
                .permitAll()
                .antMatchers("$AUTH_BASE_API/registration", "$AUTH_BASE_API/activate")
                .permitAll()
                .antMatchers("$ONE_PHASE_PATH/calc")
                .permitAll()
                .anyRequest()
                .authenticated()

        // Exception Handling
        http.exceptionHandling()
                .authenticationEntryPoint(AuthenticationEntryPoint { request, response, authException ->
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                })

        // Form Login
        http.formLogin()
                .successHandler(CustomAuthenticationSuccessHandler(emailServiceImpl, userDetailsServiceImpl))
                .failureHandler(CustomAuthenticationFailureHandler(userDetailsServiceImpl))
                .loginProcessingUrl("${AUTH_BASE_API}/login")

        // Logout
        http.logout()
                .logoutUrl("${AUTH_BASE_API}/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")

        return http.build()
    }
}
