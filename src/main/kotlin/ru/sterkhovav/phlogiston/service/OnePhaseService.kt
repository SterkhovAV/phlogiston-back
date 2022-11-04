package ru.sterkhovav.phlogiston.service

import org.springframework.stereotype.Service
import ru.sterkhovav.phlogiston.dao.repository.OnePhaseResultRepository
import ru.sterkhovav.phlogiston.dto.OnePhaseRequestDto
import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto
import ru.sterkhovav.phlogiston.dto.toEntity
import ru.sterkhovav.phlogiston.waterSteamCounter.OnePhaseParam
import ru.sterkhovav.phlogiston.waterSteamCounter.countOnePhaseParams

interface OnePhaseService {

    fun countParams(request: OnePhaseRequestDto): OnePhaseResultDto
    fun save(result: OnePhaseResultDto, userLogin: String)
    fun getUserResults(userLogin: String): List<OnePhaseResultDto>

}

@Service
class OnePhaseServiceImpl(
    private val service: OnePhaseResultRepository
) : OnePhaseService {

    override fun countParams(request: OnePhaseRequestDto): OnePhaseResultDto {
        return OnePhaseResultDto(
            pressure = request.pressure,
            temperature = request.temperature,
            specificVolume = if (request.specificVolumeConfim) countOnePhaseParams(
                request.pressure, request.temperature,
                OnePhaseParam.specific_volume
            ) else null,
            density = if (request.densityConfim) countOnePhaseParams(
                request.pressure, request.temperature,
                OnePhaseParam.density
            ) else null,
            specificEntropy = if (request.specificEntropyConfim) countOnePhaseParams(
                request.pressure, request.temperature,
                OnePhaseParam.specific_entropy
            ) else null,
            specificEnthalpy = if (request.specificEnthalpyConfim) countOnePhaseParams(
                request.pressure, request.temperature,
                OnePhaseParam.specific_enthalpy
            ) else null,
            specificInternalEnergy = if (request.specificInternalEnergyConfim) countOnePhaseParams(
                request.pressure, request.temperature,
                OnePhaseParam.specific_internal_energy
            ) else null,
            specificHeatCapacityP = if (request.specificHeatCapacityPConfim) countOnePhaseParams(
                request.pressure, request.temperature,
                OnePhaseParam.specific_heat_capacity_constant_pressure
            ) else null,
            specificHeatCapacityV = if (request.specificHeatCapacityVConfim) countOnePhaseParams(
                request.pressure, request.temperature,
                OnePhaseParam.specific_heat_capacity_constant_volume
            ) else null,
        )
    }

    override fun save(result: OnePhaseResultDto, userLogin: String) {
        service.save(result.toEntity(userLogin))
    }

    override fun getUserResults(userLogin: String): List<OnePhaseResultDto> {
        return service.getByUserLogin(userLogin)
    }

}