package ru.sterkhovav.phlogiston.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.sterkhovav.phlogiston.dto.OnePhaseRequestDto
import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto
import ru.sterkhovav.phlogiston.service.OnePhaseServiceImpl


@RestController
@RequestMapping(path = [ONE_PHASE_PATH], produces = [MediaType.APPLICATION_JSON_VALUE])
class OnePhaseController(
    private val service: OnePhaseServiceImpl
) {

    @PostMapping("/calc")
    fun calc(
        @RequestBody requestDto: OnePhaseRequestDto
    ): OnePhaseResultDto = service.countParams(requestDto)

    @PostMapping("/save")
    fun save(
        @RequestParam userLogin:String,
        @RequestBody resultDto: OnePhaseResultDto
    ) :ResponseEntity<Any?> {
        service.save(resultDto,userLogin)
        return ResponseEntity<Any?>("Saved", HttpStatus.OK)
    }

    @GetMapping("/get-user-results")
    fun get(
        @RequestParam userLogin:String
    ): List<OnePhaseResultDto>  = service.getUserResults(userLogin)
}