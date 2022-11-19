package ru.sterkhovav.phlogiston.dto

import ru.sterkhovav.phlogiston.dao.models.OnePhaseResult
import ru.sterkhovav.phlogiston.utils.OnePhaseParam


class OnePhaseRequestDto(
    val initialParams: Map<OnePhaseParam, Double>,
    val requestParams: List<OnePhaseParam>,
)

class OnePhaseResultDto(
    val initialParams: Map<OnePhaseParam, Double>,
    val countResult: Map<OnePhaseParam, Double>,
) {
    fun toEntity(username: String) = OnePhaseResult(
        author = username,
        initialParams = this.initialParams,
        countResult = this.countResult
    )
}