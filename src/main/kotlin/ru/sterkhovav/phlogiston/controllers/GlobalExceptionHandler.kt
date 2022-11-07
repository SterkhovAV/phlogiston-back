package ru.sterkhovav.phlogiston.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.sterkhovav.phlogiston.utils.*


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotCreatedException::class)
    fun catchResourceNotFoundException(e: UserNotCreatedException): ResponseEntity<ResponseMessages> {
        return ResponseEntity<ResponseMessages>(ResponseMessages(e.errors), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UserRoleNotFoundException::class)
    fun catchResourceNotFoundException(e: UserRoleNotFoundException): ResponseEntity<ResponseMessage> {
        return ResponseEntity<ResponseMessage>(ResponseMessage(e.error), HttpStatus.BAD_REQUEST)
    }
}