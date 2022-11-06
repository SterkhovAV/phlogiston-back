package ru.sterkhovav.phlogiston.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.sterkhovav.phlogiston.utils.ResponseMessage
import ru.sterkhovav.phlogiston.utils.ResponseMessages
import ru.sterkhovav.phlogiston.utils.UserNotCreatedException


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotCreatedException::class)
    fun catchResourceNotFoundException(e: UserNotCreatedException): ResponseEntity<ResponseMessages> {
        return ResponseEntity<ResponseMessages>(ResponseMessages(e.errors), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAuthenticationException(
        e: AccessDeniedException
    ): ResponseEntity<ResponseMessage> {
        return ResponseEntity<ResponseMessage>(ResponseMessage("Unauthorized access"), HttpStatus.UNAUTHORIZED)
    }
}