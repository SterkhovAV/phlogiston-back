package ru.sterkhovav.phlogiston.waterSteamCounter

import ru.sterkhovav.phlogiston.waterSteamCounter.Fi3.*
import java.lang.Math.pow
import kotlin.math.ln



//Коэффициенты и показатели степени для основного уравнения области 1 (жидкость)
data class firstZoneCoefficientsElem(val I1: Int, val J1: Int, val n1: Double)

val firstZoneCoefficients = listOf(
    firstZoneCoefficientsElem(0, -2, 1.4632971213167 * pow(10.0, -1.0)),
    firstZoneCoefficientsElem(0, -1, -8.454818716911399 * pow(10.0, -1.0)),
    firstZoneCoefficientsElem(0, 0, -3.756360367204),
    firstZoneCoefficientsElem(0, 1, 3.3855169168385),
    firstZoneCoefficientsElem(0, 2, -9.5791963387872 * pow(10.0, -1.0)),
    firstZoneCoefficientsElem(0, 3, 1.5772038513228 * pow(10.0, -1.0)),
    firstZoneCoefficientsElem(0, 4, -1.6616417199501 * pow(10.0, -2.0)),
    firstZoneCoefficientsElem(0, 5, 8.1214629983568 * pow(10.0, -4.0)),
    firstZoneCoefficientsElem(1, -9, 2.8319080123803997 * pow(10.0, -4.0)),
    firstZoneCoefficientsElem(1, -7, -6.0706301565874 * pow(10.0, -4.0)),
    firstZoneCoefficientsElem(1, -1, -1.8990068218419 * pow(10.0, -2.0)),
    firstZoneCoefficientsElem(1, 0, -3.2529748770505 * pow(10.0, -2.0)),
    firstZoneCoefficientsElem(1, 1, -2.1841717175413997 * pow(10.0, -2.0)),
    firstZoneCoefficientsElem(1, 3, -5.283835796993 * pow(10.0, -5.0)),
    firstZoneCoefficientsElem(2, -3, -4.7184321073267 * pow(10.0, -4.0)),
    firstZoneCoefficientsElem(2, 0, -3.0001780793026005 * pow(10.0, -4.0)),
    firstZoneCoefficientsElem(2, 1, 4.7661393906987 * pow(10.0, -5.0)),
    firstZoneCoefficientsElem(2, 3, -4.4141845330846 * pow(10.0, -6.0)),
    firstZoneCoefficientsElem(2, 17, -7.2694996297594 * pow(10.0, -16.0)),
    firstZoneCoefficientsElem(3, -4, -3.1679644845054 * pow(10.0, -5.0)),
    firstZoneCoefficientsElem(3, 0, -2.8270797985312 * pow(10.0, -6.0)),
    firstZoneCoefficientsElem(3, 6, -8.5205128120103 * pow(10.0, -10.0)),
    firstZoneCoefficientsElem(4, -5, -2.2425281908 * pow(10.0, -6.0)),
    firstZoneCoefficientsElem(4, -2, -6.517122289560099 * pow(10.0, -7.0)),
    firstZoneCoefficientsElem(4, 10, -1.4341729937924 * pow(10.0, -13.0)),
    firstZoneCoefficientsElem(5, -8, -4.0516996860117 * pow(10.0, -7.0)),
    firstZoneCoefficientsElem(8, -11, -1.2734301741641 * pow(10.0, -9.0)),
    firstZoneCoefficientsElem(8, -6, -1.7424871230634 * pow(10.0, -10.0)),
    firstZoneCoefficientsElem(21, -29, -6.8762131295531 * pow(10.0, -19.0)),
    firstZoneCoefficientsElem(23, -31, 1.4478307828521 * pow(10.0, -20.0)),
    firstZoneCoefficientsElem(29, -38, 2.6335781662795004 * pow(10.0, -23.0)),
    firstZoneCoefficientsElem(30, -39, -1.1947622640071003 * pow(10.0, -23.0)),
    firstZoneCoefficientsElem(31, -40, 1.8228094581404002 * pow(10.0, -24.0)),
    firstZoneCoefficientsElem(32, -41, -9.353708729245799 * pow(10.0, -26.0))
)

//Коэффициенты и показатели степени для основного уравнения области 2 (перегретый пар)
//идеальногазовое состояние
data class secondZoneIdealCoefficientsElem(val J02: Int, val n02: Double)

val secondZoneIdealCoefficients = listOf(
    secondZoneIdealCoefficientsElem(0, -9.6927686500217),
    secondZoneIdealCoefficientsElem(1, 10.086655968018),
    secondZoneIdealCoefficientsElem(-5, -0.005608791128302),
    secondZoneIdealCoefficientsElem(-4, 0.071452738081455),
    secondZoneIdealCoefficientsElem(-3, -0.40710498223928),
    secondZoneIdealCoefficientsElem(-2, 1.4240819171444),
    secondZoneIdealCoefficientsElem(-1, -4.383951131945),
    secondZoneIdealCoefficientsElem(2, -0.28408632460772),
    secondZoneIdealCoefficientsElem(3, 0.021268463753307)
)

//коэффициенты реальной составляющей
data class secondZoneRealCoefficientsElem(val I2: Int, val J2: Int, val n2: Double)

val secondZoneRealCoefficients = listOf(
    secondZoneRealCoefficientsElem(1, 0, -1.7731742473213 * pow(10.0, -3.0)),
    secondZoneRealCoefficientsElem(1, 1, -1.7834862292357998 * pow(10.0, -2.0)),
    secondZoneRealCoefficientsElem(1, 2, -4.5996013696365 * pow(10.0, -2.0)),
    secondZoneRealCoefficientsElem(1, 3, -5.7581259083431995 * pow(10.0, -2.0)),
    secondZoneRealCoefficientsElem(1, 6, -5.0325278727930005 * pow(10.0, -2.0)),
    secondZoneRealCoefficientsElem(2, 1, -3.3032641670203 * pow(10.0, -5.0)),
    secondZoneRealCoefficientsElem(2, 2, -1.8948987516315 * pow(10.0, -4.0)),
    secondZoneRealCoefficientsElem(2, 4, -3.9392777243355 * pow(10.0, -3.0)),
    secondZoneRealCoefficientsElem(2, 7, -4.3797295650673 * pow(10.0, -2.0)),
    secondZoneRealCoefficientsElem(2, 36, -2.6674547914087 * pow(10.0, -5.0)),
    secondZoneRealCoefficientsElem(3, 0, 2.0481737692309 * pow(10.0, -8.0)),
    secondZoneRealCoefficientsElem(3, 1, 4.3870667284435 * pow(10.0, -7.0)),
    secondZoneRealCoefficientsElem(3, 3, -3.227767723857 * pow(10.0, -5.0)),
    secondZoneRealCoefficientsElem(3, 6, -1.5033924542148 * pow(10.0, -3.0)),
    secondZoneRealCoefficientsElem(3, 35, -4.0668253562649 * pow(10.0, -2.0)),
    secondZoneRealCoefficientsElem(4, 1, -7.8847309559367 * pow(10.0, -10.0)),
    secondZoneRealCoefficientsElem(4, 2, 1.2790717852285 * pow(10.0, -8.0)),
    secondZoneRealCoefficientsElem(4, 3, 4.822537271850699 * pow(10.0, -7.0)),
    secondZoneRealCoefficientsElem(5, 7, 2.2922076337661 * pow(10.0, -6.0)),
    secondZoneRealCoefficientsElem(6, 3, -1.6714766451061003 * pow(10.0, -11.0)),
    secondZoneRealCoefficientsElem(6, 16, -2.1171472321355 * pow(10.0, -3.0)),
    secondZoneRealCoefficientsElem(6, 35, -2.3895741934104 * pow(10.0, 1.0)),
    secondZoneRealCoefficientsElem(7, 0, -5.905956432427 * pow(10.0, -18.0)),
    secondZoneRealCoefficientsElem(7, 11, -1.2621808899101 * pow(10.0, -6.0)),
    secondZoneRealCoefficientsElem(7, 25, -3.8946842435739004 * pow(10.0, -2.0)),
    secondZoneRealCoefficientsElem(8, 8, 1.1256211360459 * pow(10.0, -11.0)),
    secondZoneRealCoefficientsElem(8, 36, -8.2311340897998),
    secondZoneRealCoefficientsElem(9, 13, 1.9809712802088 * pow(10.0, -8.0)),
    secondZoneRealCoefficientsElem(10, 4, 1.0406965210174 * pow(10.0, -19.0)),
    secondZoneRealCoefficientsElem(10, 10, -1.0234747095929 * pow(10.0, -13.0)),
    secondZoneRealCoefficientsElem(10, 14, -1.0018179379511 * pow(10.0, -9.0)),
    secondZoneRealCoefficientsElem(16, 29, -8.0882908646985 * pow(10.0, -11.0)),
    secondZoneRealCoefficientsElem(16, 50, 1.0693031879408998 * pow(10.0, -1.0)),
    secondZoneRealCoefficientsElem(18, 57, -3.3662250574170995 * pow(10.0, -1.0)),
    secondZoneRealCoefficientsElem(20, 20, 8.918584535542099 * pow(10.0, -25.0)),
    secondZoneRealCoefficientsElem(20, 35, 3.0629316876232 * pow(10.0, -13.0)),
    secondZoneRealCoefficientsElem(20, 48, -4.2002467698208 * pow(10.0, -6.0)),
    secondZoneRealCoefficientsElem(21, 21, -5.905602968563899 * pow(10.0, -26.0)),
    secondZoneRealCoefficientsElem(22, 53, 3.7826947613457 * pow(10.0, -6.0)),
    secondZoneRealCoefficientsElem(23, 39, -1.2768608934681 * pow(10.0, -15.0)),
    secondZoneRealCoefficientsElem(24, 26, 7.308761059506102 * pow(10.0, -29.0)),
    secondZoneRealCoefficientsElem(24, 40, 5.5414715350778 * pow(10.0, -17.0)),
    secondZoneRealCoefficientsElem(24, 58, -9.436970724121 * pow(10.0, -7.0))
)

//Коэффициенты и показатели степени для основного уравнения области 3 (околокритическая область)
data class thirdZoneCoefficientsElem(val I3: Int, val J3: Int, val n3: Double)

val thirdZoneCoefficients = listOf(
    thirdZoneCoefficientsElem(0, 0, 1.0658070028513),
    thirdZoneCoefficientsElem(0, 0, -1.5732845290239 * pow(10.0, 1.0)),
    thirdZoneCoefficientsElem(0, 1, 2.0944396974307002 * pow(10.0, 1.0)),
    thirdZoneCoefficientsElem(0, 2, -7.6867707878716),
    thirdZoneCoefficientsElem(0, 7, 2.6185947787954),
    thirdZoneCoefficientsElem(0, 10, -2.808078114862),
    thirdZoneCoefficientsElem(0, 12, 1.2053369696517),
    thirdZoneCoefficientsElem(0, 23, -8.4566812812502 * pow(10.0, -3.0)),
    thirdZoneCoefficientsElem(1, 2, -1.2654315477714),
    thirdZoneCoefficientsElem(1, 6, -1.1524407806681),
    thirdZoneCoefficientsElem(1, 15, 8.8521043984318 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(1, 17, -6.4207765181607 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(2, 0, 3.8493460186671 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(2, 2, -8.5214708824206 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(2, 6, 4.8972281541877),
    thirdZoneCoefficientsElem(2, 7, -3.0502617256965),
    thirdZoneCoefficientsElem(2, 22, 3.9420536879154 * pow(10.0, -2.0)),
    thirdZoneCoefficientsElem(2, 26, 1.2558408424308 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(3, 0, -2.799932969871 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(3, 2, 1.389979956946),
    thirdZoneCoefficientsElem(3, 4, -2.018991502357),
    thirdZoneCoefficientsElem(3, 16, -8.2147637173963 * pow(10.0, -3.0)),
    thirdZoneCoefficientsElem(3, 26, -4.759603573492299 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(4, 0, 4.39840744735 * pow(10.0, -2.0)),
    thirdZoneCoefficientsElem(4, 2, -4.4476435428739 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(4, 4, 9.0572070719733 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(4, 26, 7.0522450087967 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(5, 1, 1.0770512626332 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(5, 3, -3.2913623258954 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(5, 26, -5.087106204115799 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(6, 0, -2.2175400873096 * pow(10.0, -2.0)),
    thirdZoneCoefficientsElem(6, 2, 9.4260751665092 * pow(10.0, -2.0)),
    thirdZoneCoefficientsElem(6, 26, 1.6436278447961 * pow(10.0, -1.0)),
    thirdZoneCoefficientsElem(7, 2, -1.3503372241348 * pow(10.0, -2.0)),
    thirdZoneCoefficientsElem(8, 26, -1.4834345352472 * pow(10.0, -2.0)),
    thirdZoneCoefficientsElem(9, 2, 5.7922953628084 * pow(10.0, -4.0)),
    thirdZoneCoefficientsElem(9, 26, 3.2308904703711 * pow(10.0, -3.0)),
    thirdZoneCoefficientsElem(10, 0, 8.0964802996215 * pow(10.0, -5.0)),
    thirdZoneCoefficientsElem(10, 1, -1.6557679795037 * pow(10.0, -4.0)),
    thirdZoneCoefficientsElem(11, 26, -4.4923899061815 * pow(10.0, -5.0)),
)

//Коэффициенты и показатели степени для основного уравнения области 4 (линия насыщения)
data class fourthZoneCoefficientsElem(val n4: Double)

val fourthZoneCoefficients = listOf(
    fourthZoneCoefficientsElem(1167.0521452767),
    fourthZoneCoefficientsElem(-724213.16703206),
    fourthZoneCoefficientsElem(-17.073846940092),
    fourthZoneCoefficientsElem(12020.82470247),
    fourthZoneCoefficientsElem(-3232555.0322333),
    fourthZoneCoefficientsElem(14.91510861353),
    fourthZoneCoefficientsElem(-4823.2657361591),
    fourthZoneCoefficientsElem(405113.40542057),
    fourthZoneCoefficientsElem(-0.23855557567849),
    fourthZoneCoefficientsElem(650.17534844798)
)

enum class Gamma1 {
    PI, PI_PI, TAU, TAU_TAU, PI_TAU
}

/**
 * Основное уравнение для области 1 (область жидкости)
 * @param p pressure (давление) МПа
 * @param t temperature (температура) K
 * @param param имя производной
 */
fun countGamma1(p: Double, t: Double, param: Gamma1? = null): Double {
    val pi = p / 16.53
    val tau = 1386 / t

    var gamma1 = 0.0
    var gammaPi1 = 0.0
    var gammaPiPi1 = 0.0
    var gammaTau1 = 0.0
    var gammaTauTau1 = 0.0
    var gammaPiTau1 = 0.0

    firstZoneCoefficients.forEach {
        fun firstMultiplier() = pow(7.1 - pi, it.I1.toDouble())

        //Первая частная производная уравнения для области 1 по Pi
        fun firstMultiplierPi() = -it.I1.toDouble() * pow(7.1 - pi, it.I1.toDouble() - 1)

        //Вторая частная производная уравнения для области 1 по Pi/Pi
        fun firstMultiplierPiPi() = it.I1.toDouble() * (it.I1.toDouble() - 1) * pow(7.1 - pi, it.I1.toDouble() - 2)

        fun secondMultiplier() = pow(tau - 1.222, it.J1.toDouble())

        //Первая частная производная уравнения для области 1 по Tau
        fun secondMultiplierTau() = it.J1.toDouble() * pow(tau - 1.222, it.J1.toDouble() - 1)

        //Вторая частная производная уравнения для области 1 по Tau/Tau
        fun secondMultiplierTauTau() =
            it.J1.toDouble() * (it.J1.toDouble() - 1) * pow(tau - 1.222, it.J1.toDouble() - 2)


        when (param) {
            null -> gamma1 += it.n1 * firstMultiplier() * secondMultiplier()
            Gamma1.PI -> gammaPi1 += it.n1 * firstMultiplierPi() * secondMultiplier()
            Gamma1.PI_PI -> gammaPiPi1 += it.n1 * firstMultiplierPiPi() * secondMultiplier()
            Gamma1.TAU -> gammaTau1 += it.n1 * firstMultiplier() * secondMultiplierTau()
            Gamma1.TAU_TAU -> gammaTauTau1 += it.n1 * firstMultiplier() * secondMultiplierTauTau()
            Gamma1.PI_TAU -> gammaPiTau1 += it.n1 * firstMultiplierPi() * secondMultiplierTau()
        }
    }

    return when (param) {
        null -> gamma1
        Gamma1.PI -> gammaPi1
        Gamma1.PI_PI -> gammaPiPi1
        Gamma1.TAU -> gammaTau1
        Gamma1.TAU_TAU -> gammaTauTau1
        Gamma1.PI_TAU -> gammaPiTau1
    }
}

enum class Gamma2 {
    PI, PI_PI, TAU, TAU_TAU, PI_TAU
}

/**
 * Основное уравнение для области 2 (область жидкости)
 * @param p pressure (давление) МПа
 * @param t temperature (температура) K
 * @param param имя производной
 */
fun countGamma2(p: Double, t: Double, param: Gamma2? = null): Double {
    val tau = 540 / t

    var gamma2 = ln(p)
    var gammaPi2 = 1 / p
    var gammaPiPi2 = -1 / pow(p, 2.0)
    var gammaTau2 = 0.0
    var gammaTauTau2 = 0.0
    var gammaPiTau2 = 0.0

    secondZoneIdealCoefficients.forEach {
        when {
            param == null -> gamma2 += it.n02 * pow(tau, it.J02.toDouble())
            param == Gamma2.TAU -> gammaTau2 += it.n02 * it.J02.toDouble() * pow(tau, it.J02.toDouble() - 1)
            param == Gamma2.TAU_TAU -> gammaTauTau2 += it.n02 * it.J02.toDouble() * (it.J02.toDouble() - 1) * pow(
                tau, it.J02.toDouble() - 2
            )
        }
    }

    secondZoneRealCoefficients.forEach {
        fun firstMultiplier() = pow(p, it.I2.toDouble())

        //Первая частная производная уравнения для области 2 по Pi
        fun firstMultiplierPi() = it.I2.toDouble() * pow(p, it.I2.toDouble() - 1)

        //Вторая частная производная уравнения для области 2 по Pi/Pi
        fun firstMultiplierPiPi() = it.I2.toDouble() * (it.I2.toDouble() - 1) * pow(p, it.I2.toDouble() - 2)

        fun secondMultiplier() = pow(tau - 0.5, it.J2.toDouble())

        //Первая частная производная уравнения для области 2 по Tau
        fun secondMultiplierTau() = it.J2.toDouble() * pow(tau - 0.5, it.J2.toDouble() - 1)

        //Вторая частная производная уравнения для области 2 по Tau/Tau
        fun secondMultiplierTauTau() =
            it.J2.toDouble() * (it.J2.toDouble() - 1) * pow(tau - 0.5, it.J2.toDouble() - 2)

        when (param) {
            null -> gamma2 += it.n2 * firstMultiplier() * secondMultiplier()
            Gamma2.PI -> gammaPi2 += it.n2 * firstMultiplierPi() * secondMultiplier()
            Gamma2.PI_PI -> gammaPiPi2 += it.n2 * firstMultiplierPiPi() * secondMultiplier()
            Gamma2.TAU -> gammaTau2 += it.n2 * firstMultiplier() * secondMultiplierTau()
            Gamma2.TAU_TAU -> gammaTauTau2 += it.n2 * firstMultiplier() * secondMultiplierTauTau()
            Gamma2.PI_TAU -> gammaPiTau2 += it.n2 * firstMultiplierPi() * secondMultiplierTau()
        }
    }
    return when (param) {
        null -> gamma2
        Gamma2.PI -> gammaPi2
        Gamma2.PI_PI -> gammaPiPi2
        Gamma2.TAU -> gammaTau2
        Gamma2.TAU_TAU -> gammaTauTau2
        Gamma2.PI_TAU -> gammaPiTau2
    }
}


enum class Fi3 {
    DELTA, DELTA_DELTA, DELTA_DELTA_DELTA, TAU, TAU_TAU, DELTA_TAU
}

/**
 * Основное уравнение для области 3 (околокритическая область)
 * @param ro density (плотность) кг/м3
 * @param t temperature (температура) K
 * @param param имя производной
 */
fun countFi3(ro: Double, t: Double, param: Fi3? = null): Double {
    val delta = ro / 322
    val tau = 647.096 / t

    var fi3 = thirdZoneCoefficients[0].n3 * ln(delta)
    var fiDelta3 = thirdZoneCoefficients[0].n3 / delta
    var fiDeltaDelta3 = -thirdZoneCoefficients[0].n3 / pow(delta, 2.0)
    var fiDeltaDeltaDelta3 = 2 * thirdZoneCoefficients[0].n3 / pow(delta, 3.0)
    var fiTau3 = 0.0
    var fiTauTau3 = 0.0
    var fiDeltaTau3 = 0.0
    for (i in 1 until thirdZoneCoefficients.size) {
        with(thirdZoneCoefficients[i]) {
            fun firstMultiplier() = pow(delta, this.I3.toDouble())

            //Первая частная производная уравнения для области 3 по Delta
            fun firstMultiplierDelta() = this.I3.toDouble() * pow(delta, this.I3.toDouble() - 1)

            //Вторая частная производная уравнения для области 3 по Delta/Delta
            fun firstMultiplierDeltaDelta() =
                (this.I3.toDouble() - 1) * this.I3.toDouble() * pow(delta, this.I3.toDouble() - 2)

            //Третья частная производная уравнения для области 3 по Delta/Delta/Delta
            fun firstMultiplierDeltaDeltaDelta() =
                (this.I3.toDouble() - 2) * (this.I3.toDouble() - 1) * this.I3.toDouble() * pow(
                    delta, this.I3.toDouble() - 3
                )

            fun secondMultiplier() = pow(tau, this.J3.toDouble())

            //Первая частная производная уравнения для области 3 по Tau
            fun secondMultiplierTau() = this.J3.toDouble() * pow(tau, this.J3.toDouble() - 1)

            //Вторая частная производная уравнения для области 3 по Tau/Tau
            fun secondMultiplierTauTau() =
                (this.J3.toDouble() - 1) * this.J3.toDouble() * pow(tau, this.J3.toDouble() - 2)


            when (param) {
                null -> fi3 += this.n3 * firstMultiplier() * secondMultiplier()
                DELTA -> fiDelta3 += this.n3 * firstMultiplierDelta() * secondMultiplier()
                DELTA_DELTA -> fiDeltaDelta3 += this.n3 * firstMultiplierDeltaDelta() * secondMultiplier()
                DELTA_DELTA_DELTA -> fiDeltaDeltaDelta3 += this.n3 * firstMultiplierDeltaDeltaDelta() * secondMultiplier()
                TAU -> fiTau3 += this.n3 * firstMultiplier() * secondMultiplierTau()
                TAU_TAU -> fiTauTau3 += this.n3 * firstMultiplier() * secondMultiplierTauTau()
                DELTA_TAU -> fiDeltaTau3 += this.n3 * firstMultiplierDelta() * secondMultiplierTau()
            }
        }
    }
    return when (param) {
        null -> fi3
        DELTA -> fiDelta3
        DELTA_DELTA -> fiDeltaDelta3
        DELTA_DELTA_DELTA -> fiDeltaDeltaDelta3
        TAU -> fiTau3
        TAU_TAU -> fiTauTau3
        DELTA_TAU -> fiDeltaTau3
    }
}