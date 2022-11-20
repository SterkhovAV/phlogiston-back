package ru.sterkhovav.phlogiston.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import ru.sterkhovav.phlogiston.service.UserDetailsServiceImpl
import java.time.OffsetDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class CustomAuthenticationSuccessHandler(
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val status = userDetailsServiceImpl.getUserStatus(SecurityContextHolder.getContext().authentication.name)
        val data: MutableMap<String, String> = HashMap()
        data["timestamp"] = OffsetDateTime.now().toString()
        if (!status) {
            response!!.status = HttpStatus.UNAUTHORIZED.value()
            val auth = SecurityContextHolder.getContext().authentication
            data["message"] = "${SecurityContextHolder.getContext().authentication.name} didn't activated"
            if (auth != null) { SecurityContextLogoutHandler().logout(request, response, auth) }
        } else {
            response!!.status = HttpStatus.OK.value()
            data["message"] = "${SecurityContextHolder.getContext().authentication.name} login success"
        }
        response.outputStream.println(ObjectMapper().writeValueAsString(data))
    }
}