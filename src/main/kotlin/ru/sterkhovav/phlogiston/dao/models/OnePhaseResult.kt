package ru.sterkhovav.phlogiston.dao.models

import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "phlogiston_one_phase_result")
class OnePhaseResult(
    @field:Id
    @field:Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(name = "author")
    val author: String = "admin",  //TODO до реализации авторизации

    @field:Column(name = "create_date", nullable = false)
    var createDate: LocalDateTime = LocalDateTime.now(),

    @field:Column(name = "pressure")
    val pressure: Double? = null,

    @field:Column(name = "temperature")
    val temperature: Double? = null,

    @field:Column(name = "specific_volume")
    val specificVolume: Double? = null,

    @field:Column(name = "density")
    val density: Double? = null,

    @field:Column(name = "specific_entropy")
    val specificEntropy: Double? = null,

    @field:Column(name = "specific_enthalpy")
    val specificEnthalpy: Double? = null,

    @field:Column(name = "specific_internal_energy")
    val specificInternalEnergy: Double? = null,

    @field:Column(name = "specific_heat_capacity_p")
    val specificHeatCapacityP: Double? = null,

    @field:Column(name = "specific_heat_capacity_v")
    val specificHeatCapacityV: Double? = null
) {
    fun toDto() = OnePhaseResultDto(
        pressure = this.pressure,
        temperature = this.temperature,
        specificVolume = this.specificVolume,
        density = this.density,
        specificEntropy = this.specificEntropy,
        specificEnthalpy = this.specificEnthalpy,
        specificInternalEnergy = this.specificInternalEnergy,
        specificHeatCapacityP = this.specificHeatCapacityP,
        specificHeatCapacityV = this.specificHeatCapacityV
    )
}



