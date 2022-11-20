package ru.sterkhovav.phlogiston.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import ru.sterkhovav.phlogiston.dto.UserDto
import ru.sterkhovav.phlogiston.dto.UserRegistrationRequestDto
import ru.sterkhovav.phlogiston.service.UserServiceImpl
import ru.sterkhovav.phlogiston.utils.ResponseMessage
import java.util.*


@RestController
@RequestMapping(path = [AUTH_BASE_API])
class AuthController(
    private val userServiceImpl: UserServiceImpl,
    private val passwordEncoder: PasswordEncoder,
) {

    @PostMapping("/registration")
    fun registration(
        @RequestBody userRegistrationRequestDto: UserRegistrationRequestDto,
    ): ResponseEntity<ResponseMessage> {
        userRegistrationRequestDto.password = passwordEncoder.encode(userRegistrationRequestDto.password)
        userServiceImpl.register(userRegistrationRequestDto)
        return ResponseEntity<ResponseMessage>(
            ResponseMessage("User successfully saved, activation link sent to your email"),
            HttpStatus.OK
        )
    }

    @GetMapping("/get-user")
    fun getUser(): UserDto {
        return userServiceImpl.getUserByUsername(SecurityContextHolder.getContext().authentication.name)
    }

    @GetMapping("/activate")
    fun getUser(
        @RequestParam activation_code: UUID
    ):ResponseEntity<ResponseMessage> {
        userServiceImpl.activateUser(activation_code)
        return ResponseEntity<ResponseMessage>(
            ResponseMessage("User successfully activated"),
            HttpStatus.OK
        )
    }

}