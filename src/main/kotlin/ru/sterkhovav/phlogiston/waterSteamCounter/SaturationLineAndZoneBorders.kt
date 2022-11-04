package ru.sterkhovav.phlogiston.waterSteamCounter

import ru.sterkhovav.phlogiston.utils.PRESSURE_SATURATUION_LINE_ERROR
import ru.sterkhovav.phlogiston.utils.TEMPERATURE_SATURATUION_LINE_ERROR
import ru.sterkhovav.phlogiston.waterSteamCounter.fourthZoneCoefficients

/**
 * Уравнение линии насыщения (4 область) (давление от температуры) (МПа)
 * @param t temperature (температура) K
 */
fun countSaturationLinePressureByTemp(t: Double): Double {
    check(t <= 647.096) { TEMPERATURE_SATURATUION_LINE_ERROR }
    with(fourthZoneCoefficients) {
        val localCoefficient = t + this[8].n4 / (t - this[9].n4)
        val Apt = Math.pow(localCoefficient, 2.0) + this[0].n4 * localCoefficient + this[1].n4
        val Bpt = this[2].n4 * Math.pow(localCoefficient, 2.0) + this[3].n4 * localCoefficient + this[4].n4
        val Cpt = this[5].n4 * Math.pow(localCoefficient, 2.0) + this[6].n4 * localCoefficient + this[7].n4
        return Math.pow((2 * Cpt) / (-Bpt + Math.pow((Math.pow(Bpt, 2.0) - 4 * Apt * Cpt), 0.5)), 4.0)
    }
}

/**
 * Уравнение линии насыщения (4 область) (температура от давления) К
 * @param p pressure (давление) МПа
 */
fun countSaturationLineTempByPressure(p: Double): Double {
    check(p <= 22.064) { PRESSURE_SATURATUION_LINE_ERROR }
    with(fourthZoneCoefficients) {
        val localCoefficient = Math.pow(p, 0.25)
        val Ept = Math.pow(localCoefficient, 2.0) + this[2].n4 * localCoefficient + this[5].n4
        val Fpt = this[0].n4 * Math.pow(localCoefficient, 2.0) + this[3].n4 * localCoefficient + this[6].n4
        val Gpt = this[1].n4 * Math.pow(localCoefficient, 2.0) + this[4].n4 * localCoefficient + this[7].n4
        val Dpt = 2 * Gpt / (-Fpt - Math.pow(Math.pow(Fpt, 2.0) - 4 * Ept * Gpt, 0.5))
        return (this[9].n4 + Dpt - Math.pow(
            Math.pow(this[9].n4 + Dpt, 2.0) - 4 * (this[8].n4 + this[9].n4 * Dpt), 0.5
        )) / 2
    }
}

/**
 * Граница областей 2 и 3 (температура) К
 * @param p pressure (давление) МПа
 */
fun countBorderZone23byPressure(p: Double): Double {
    return 572.54459862746 + Math.pow((p - 13.91883977887) / (1.0192970039326 * Math.pow(10.0, -3.0)), 0.5)
}

/**
 * Граница областей 2 и 3 (давление) (МПа)
 * @param t temperature (температура) К
 */
fun countBorderZone23byTemp(t: Double): Double {
    return 348.05185628969 - 1.1671859879975 * t + 1.0192970039326 * Math.pow(
        10.0,
        -3.0
    ) * Math.pow(t, 2.0)
}