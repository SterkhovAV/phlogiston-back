package ru.sterkhovav.phlogiston.dto

import ru.sterkhovav.phlogiston.dao.models.OnePhaseResult

class OnePhaseRequestDto(
    val pressure: Double,
    val temperature: Double,
    val specificVolumeConfim: Boolean = false,
    val densityConfim: Boolean = false,
    val specificEntropyConfim: Boolean = false,
    val specificEnthalpyConfim: Boolean = false,
    val specificInternalEnergyConfim: Boolean = false,
    val specificHeatCapacityPConfim: Boolean = false,
    val specificHeatCapacityVConfim: Boolean = false,
)

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
) {
    fun toEntity(username: String) = OnePhaseResult(
        author = username,
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
}