package ru.sterkhovav.phlogiston.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.sterkhovav.phlogiston.dto.OnePhaseRequestDto
import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto
import ru.sterkhovav.phlogiston.service.OnePhaseService


@RestController
@RequestMapping(path = [ONE_PHASE_PATH], produces = [MediaType.APPLICATION_JSON_VALUE])
class OnePhaseController(
    private val service: OnePhaseService,
) {

    @PostMapping("/calc")
    fun calc(
        @RequestBody requestDto: OnePhaseRequestDto
    ): OnePhaseResultDto = service.countParams(requestDto)

    @PostMapping("/save")
    fun save(
        @RequestBody resultDto: OnePhaseResultDto
    ) :ResponseEntity<Any?> {
        service.save(resultDto,SecurityContextHolder.getContext().authentication.name)
        return ResponseEntity<Any?>("Saved", HttpStatus.OK)
    }

    @GetMapping("/get-user-results")
    fun get(): List<OnePhaseResultDto>  = service.getUserResults(SecurityContextHolder.getContext().authentication.name)


}