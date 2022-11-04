package ru.sterkhovav.phlogiston.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.sterkhovav.phlogiston.dao.models.OnePhaseResult
import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto

interface OnePhaseResultRepository : JpaRepository<OnePhaseResult, Long> {
    fun getByUserLogin(userLogin: String): List<OnePhaseResultDto>
}