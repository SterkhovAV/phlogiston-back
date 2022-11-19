package ru.sterkhovav.phlogiston.utils

import org.apache.commons.math3.util.Precision


val TEMPERATURE_SATURATUION_LINE_ERROR = "Temperature on saturation line is higher then critical (647.096 K)"
val PRESSURE_SATURATUION_LINE_ERROR = "Pressure on saturation line is higher then critical (22.064 MPa)"
val MAIN_ERROR = "The entered data is out of bounds"

fun Double.round(n: Int) =
    Precision.round(this, n)

enum class OnePhaseParam{
    PRESSURE, TEMPERATURE, SPECIFIC_VOLUME, DENSITY, SPECIFIC_ENTROPY, SPECIFIC_ENTHALPY, SPECIFIC_INTERNAL_ENERGY,
    SPECIFIC_HEAT_CAPACITY_P, SPECIFIC_HEAT_CAPACITY_V
}

//enum class OnePhaseParam(val strRepr: String) {
//    specific_volume("Удельный объем (м3/кг)"),
//    density("Плотность (кг/м3)"),
//    specific_entropy("Удельная энтропия (кДж/(кг*К))"),
//    specific_enthalpy("Удельная энтальпия (кДж/кг)"),
//    specific_internal_energy("Удельная внутренняя энергия (кДж/кг)"),
//    specific_heat_capacity_constant_pressure("Удельная изобарная теплоемкость (кДж/(кг*К))"),
//    specific_heat_capacity_constant_volume("Удельная изохорная теплоемкость (кДж/(кг*К))")
//}