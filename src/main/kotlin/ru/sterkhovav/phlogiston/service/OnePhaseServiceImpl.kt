package ru.sterkhovav.phlogiston.service

import org.springframework.stereotype.Service
import ru.sterkhovav.phlogiston.dao.repository.OnePhaseResultRepository
import ru.sterkhovav.phlogiston.dto.OnePhaseRequestDto
import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto
import ru.sterkhovav.phlogiston.utils.OnePhaseParam
import ru.sterkhovav.phlogiston.waterSteamCounter.countOnePhaseParamsPT

interface OnePhaseService {
    fun countParams(request: OnePhaseRequestDto): OnePhaseResultDto
    fun save(result: OnePhaseResultDto, username: String)
    fun getUserResults(userLogin: String): List<OnePhaseResultDto>
}

@Service
class OnePhaseServiceImpl(
    private val onePhaseResultRepository: OnePhaseResultRepository
) : OnePhaseService {

    override fun countParams(request: OnePhaseRequestDto): OnePhaseResultDto {

        val initialParams = mutableMapOf<OnePhaseParam, Double>()
        val pressure = request.initialParams.get(OnePhaseParam.PRESSURE)
        val temperature = request.initialParams.get(OnePhaseParam.TEMPERATURE)
        initialParams.put(OnePhaseParam.PRESSURE, pressure!!)
        initialParams.put(OnePhaseParam.TEMPERATURE, temperature!!)
        //TODO(Проверки и другие виды исходных данных)

        val countResult = countOnePhaseParamsPT(pressure,temperature,request.requestParams)

        return OnePhaseResultDto(initialParams,countResult)
    }

    override fun save(result: OnePhaseResultDto, username: String) {
        onePhaseResultRepository.save(result.toEntity(username))
    }

    override fun getUserResults(userLogin: String): List<OnePhaseResultDto> {
        return onePhaseResultRepository.getByAuthor(userLogin)
    }

}