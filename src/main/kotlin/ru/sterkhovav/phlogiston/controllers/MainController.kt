package ru.sterkhovav.phlogiston.controllers

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import ru.sterkhovav.phlogiston.dto.UserDto
import ru.sterkhovav.phlogiston.service.UserDetailsServiceImpl
import ru.sterkhovav.phlogiston.service.UserServiceImpl

@Controller
class MainController(
    private val userServiceImpl: UserServiceImpl
) {
    @GetMapping("/")
    fun firstPage(): String {
        return "firstPage"
    }
}