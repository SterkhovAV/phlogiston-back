package ru.sterkhovav.phlogiston.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sterkhovav.phlogiston.dao.models.OnePhaseResult
import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto

@Repository
interface OnePhaseResultRepository : JpaRepository<OnePhaseResult, Long> {
    fun getByAuthor(username: String): List<OnePhaseResultDto>
}