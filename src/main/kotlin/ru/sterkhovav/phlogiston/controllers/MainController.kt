package ru.sterkhovav.phlogiston.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import ru.sterkhovav.phlogiston.service.EmailServiceImpl

@Controller
class MainController {
    @GetMapping("/")
    fun firstPage(): String {
        return "firstPage"
    }
}