package ru.sterkhovav.phlogiston.dto

import ru.sterkhovav.phlogiston.dao.models.OnePhaseResult

class OnePhaseResultDto(
    val pressure: Double? = null,
    val temperature: Double? = null,
    val specificVolume: Double? = null,
    val density: Double? = null,
    val specificEntropy: Double? = null,
    val specificEnthalpy: Double? = null,
    val specificInternalEnergy: Double? = null,
    val specificHeatCapacityP: Double? = null,
    val specificHeatCapacityV: Double? = null
)

fun OnePhaseResultDto.toEntity(userLogin:String) = OnePhaseResult(
    userLogin = userLogin,
    pressure = this.pressure,
    temperature = this.temperature,
    specificVolume = this.specificVolume,
    density = this.density,
    specificEntropy = this.specificEntropy,
    specificEnthalpy = this.specificEnthalpy,
    specificInternalEnergy = this.specificInternalEnergy,
    specificHeatCapacityP = this.specificHeatCapacityP,
    specificHeatCapacityV = this.specificHeatCapacityV,
)