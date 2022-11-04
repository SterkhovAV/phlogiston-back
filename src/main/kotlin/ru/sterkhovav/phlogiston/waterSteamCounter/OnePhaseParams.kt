package ru.sterkhovav.phlogiston.waterSteamCounter

import ru.sterkhovav.phlogiston.waterSteamCounter.OnePhaseParam.*
import ru.sterkhovav.phlogiston.utils.MAIN_ERROR
import java.lang.Math.abs
import java.lang.Math.pow


/**
 * Функция вычисления плотности по давлению и температуре (3 область) (кг/м3)
 * @param p pressure (давление) МПа
 * @param t temperature (температура) K
 */
fun countDensityZone3(p: Double, t: Double): Double {

    fun countPIter(roMean: Double) =
        0.461526 * 0.001 * t * pow(roMean, 2.0) * countFi3(roMean, t, Fi3.DELTA) / 322

    fun countDensity(roLeft: Double, roRight: Double, delta: Double): Double {
        var roLeftIter = roLeft
        var roRightIter = roRight
        var roMean = (roLeftIter + roRightIter) / 2
        var pIter = countPIter(roMean)

        while (abs(p - pIter) >= delta) {
            if (p - pIter > 0) roRightIter = roMean else roLeftIter = roMean
            roMean = (roLeftIter + roRightIter) / 2
            pIter = countPIter(roMean)

        }
        return roMean
    }
    when {
        p > 22.064 && p <= 100 && t > 623.15 && t <= countBorderZone23byPressure(p) -> {
            return countDensity(765.0, 10.0, pow(10.0, -3.0))
        }
        p > countSaturationLinePressureByTemp(623.15) && p < 22.064 -> {
            when {
                t > 623.15 && t <= countSaturationLineTempByPressure(p) -> {
                    return countDensity(765.0, 322.0, pow(10.0, -7.0))
                }
                t > countSaturationLineTempByPressure(p) && t <= countBorderZone23byPressure(22.064) -> {
                    return countDensity(322.0, 10.0, pow(10.0, -7.0))
                }
                else -> throw IllegalStateException(MAIN_ERROR)
            }
        }
        else -> throw IllegalStateException(MAIN_ERROR)
    }
}

enum class OnePhaseParam(val strRepr: String) {
    specific_volume("Удельный объем (м3/кг)"),
    density("Плотность (кг/м3)"),
    specific_entropy("Удельная энтропия (кДж/(кг*К))"),
    specific_enthalpy("Удельная энтальпия (кДж/кг)"),
    specific_internal_energy("Удельная внутренняя энергия (кДж/кг)"),
    specific_heat_capacity_constant_pressure("Удельная изобарная теплоемкость (кДж/(кг*К))"),
    specific_heat_capacity_constant_volume("Удельная изохорная теплоемкость (кДж/(кг*К))")
}

/**
 * Расчет параметров однофазной области
 * @param p pressure (давление) МПа
 * @param t temperature (температура) K
 */
fun countOnePhaseParams(p: Double, t: Double, param: OnePhaseParam): Double {
    check(t >= 273.15 && t <= 1073.15) { MAIN_ERROR }
    check(p > 0.0 && p <= 100.0) { MAIN_ERROR }

    return when {
        t > 623.15 -> when {
            p <= countBorderZone23byTemp(t) -> countZone2(p, t, param)
            else -> countZone3(p, t, param)
        }
        else -> when {
            p < countSaturationLinePressureByTemp(t) -> countZone2(p, t, param)
            else -> countZone1(p, t, param)
        }
    }
}

private fun countZone1(p: Double, t: Double, param: OnePhaseParam) = when (param) {
    specific_volume -> {
        (0.461526 * t * p * countGamma1(p, t, Gamma1.PI)) / (p * 1000)
    }
    density -> {
        1 / ((0.461526 * t * p * countGamma1(p, t, Gamma1.PI) / (p * 1000)))
    }
    specific_entropy -> {
        1386 / t * countGamma1(p, t, Gamma1.TAU) - countGamma1(p, t)
    }
    specific_enthalpy -> {
        1386 * countGamma1(p, t, Gamma1.TAU) * 0.461526
    }
    specific_internal_energy -> {
        (1386 / t * countGamma1(p, t, Gamma1.TAU) - p / 16.53 *
                countGamma1(p, t, Gamma1.PI)) * t * 0.461526
    }
    specific_heat_capacity_constant_pressure -> -0.461526 * countGamma1(p, t, Gamma1.TAU_TAU) *
            pow(1386 / t, 2.0)
    specific_heat_capacity_constant_volume -> 0.461526 * (-countGamma1(p, t, Gamma1.TAU_TAU) *
            pow(1386 / t, 2.0) + pow(
        (countGamma1(p, t, Gamma1.PI) - 1386 / t *
                countGamma1(p, t, Gamma1.PI_TAU)), 2.0
    ) / countGamma1(p, t, Gamma1.PI_PI))
}

private fun countZone2(p: Double, t: Double, param: OnePhaseParam) = when (param) {
    specific_volume -> {
        (0.461526 * t * p * countGamma2(p, t, Gamma2.PI)) / (16.53 * p * 1000)
    }
    density -> {
        1 / ((0.461526 * t * p * countGamma2(p, t, Gamma2.PI)) / (16.53 * p * 1000))
    }
    specific_entropy -> {
        540 / t * countGamma2(p, t, Gamma2.TAU) - countGamma2(p, t)
    }
    specific_enthalpy -> {
        540 * countGamma2(p, t, Gamma2.TAU) * 0.461526
    }
    specific_internal_energy -> {
        (540 * countGamma2(p, t, Gamma2.TAU) - p * t * countGamma2(p, t, Gamma2.PI)) * 0.461526
    }
    specific_heat_capacity_constant_pressure -> -0.461526 * countGamma2(p, t, Gamma2.TAU_TAU) *
            pow(540 / t, 2.0)
    specific_heat_capacity_constant_volume -> 0.461526 * (-countGamma2(p, t, Gamma2.TAU_TAU) *
            pow(540 / t, 2.0) + pow(
        (countGamma2(p, t, Gamma2.PI) - 540 / t * countGamma2(p, t, Gamma2.PI_TAU)), 2.0
    ) / countGamma2(p, t, Gamma2.PI_PI))
}

private fun countZone3(p: Double, t: Double, param: OnePhaseParam): Double {
    val densityZone3 = countDensityZone3(p, t)
    return when (param) {
        specific_volume -> 1 / densityZone3
        density -> densityZone3
        specific_entropy -> {
            647.096 / t * countFi3(densityZone3, t, Fi3.TAU) - countFi3(densityZone3, t)
        }
        specific_enthalpy -> {
            (647.096 / t * countFi3(densityZone3, t, Fi3.TAU) + densityZone3 *
                    countFi3(densityZone3, t, Fi3.DELTA) / 322) * t * 0.461526
        }
        specific_internal_energy -> {
            647.096 * countFi3(densityZone3, t, Fi3.TAU) * 0.461526

        }
        specific_heat_capacity_constant_pressure -> 0.461526 * ((pow(
            (countDensityZone3(p, t) * countFi3(countDensityZone3(p, t), t, Fi3.DELTA) /
                    322 - countDensityZone3(p, t) * (647.096 / t) *
                    countFi3(countDensityZone3(p, t), t, Fi3.DELTA_TAU) / 322), 2.0
        ) / (2 * countDensityZone3(p, t) * countFi3(countDensityZone3(p, t), t, Fi3.DELTA) / 322 +
                pow(countDensityZone3(p, t), 2.0) * countFi3(countDensityZone3(p, t), t, Fi3.DELTA_DELTA) /
                pow(322.0, 2.0))) - pow(647.096 / t, 2.0) * countFi3(countDensityZone3(p, t), t, Fi3.TAU_TAU))
        specific_heat_capacity_constant_volume -> 0.461526 * (-pow(647.096 / t, 2.0) * countFi3(
            countDensityZone3(p, t), t, Fi3.TAU_TAU
        ))
    }
}