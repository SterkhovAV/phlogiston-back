package ru.sterkhovav.phlogiston.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import ru.sterkhovav.phlogiston.controllers.AUTH_BASE_API
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class CustomAuthenticationFailureHandler(
) : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest, response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.sendRedirect("$AUTH_BASE_API/loginError")
    }
}