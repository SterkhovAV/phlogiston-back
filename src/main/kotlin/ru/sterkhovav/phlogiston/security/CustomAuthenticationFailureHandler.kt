package ru.sterkhovav.phlogiston.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import ru.sterkhovav.phlogiston.controllers.AUTH_BASE_API
import ru.sterkhovav.phlogiston.service.UserServiceImpl
import java.time.OffsetDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
class CustomAuthenticationFailureHandler(
    private val userServiceImpl: UserServiceImpl
) : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        val data: MutableMap<String, String> = HashMap()
        data["timestamp"] = OffsetDateTime.now().toString()
        try {
            userServiceImpl.getUserByUsername(request.getParameter("username"))
            data["message"] = "Password is incorrect"
        } catch (e: UsernameNotFoundException) {
            data["message"] = "User is not found"
        }
        response.outputStream
            .println(ObjectMapper().writeValueAsString(data))
    }
}