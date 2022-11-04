package ru.sterkhovav.phlogiston.dto

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