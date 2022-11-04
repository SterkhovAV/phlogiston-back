package ru.sterkhovav.phlogiston.utils
import org.apache.commons.math3.util.Precision


val TEMPERATURE_SATURATUION_LINE_ERROR = "Температура на линии насыщения выше критической (647.096 К)"
val PRESSURE_SATURATUION_LINE_ERROR = "Давление на линии насыщения выше критического (22.064 МПа)"
val MAIN_ERROR = "Введенные данные выходят за границы допустимых"

fun Double.round(n: Int) =
    Precision.round(this, n)