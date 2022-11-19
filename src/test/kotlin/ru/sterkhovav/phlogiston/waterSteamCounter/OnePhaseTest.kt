package ru.sterkhovav.phlogiston.waterSteamCounter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


@Suppress("NonAsciiCharacters")
class OnePhaseTest {
    //More tests should be done!!!
    @Test
    fun `specific_volume test`() {
        val res = countOnePhaseParamsPT(2.0,30.0+273.15, OnePhaseParam.specific_volume)

        val ref = 0.01658814590723067
        assertEquals(ref, res)

    }

}