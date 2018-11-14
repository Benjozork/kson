package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.exception.IllegalJsonNumberTokenException
import me.benjozork.kson.parser.exception.IllegalJsonNumberValueException
import me.benjozork.kson.parser.internal.StatefulCharReader

import org.junit.Test

import org.junit.Assert.assertEquals

class JsonNumberValueParserTest {

    @Test
    fun intNumberTest() {

        val shouldSucceed = mapOf (
            "255" to 255,
            "-21" to -21,
            "0"   to 0
        )

        shouldSucceed.forEach { source, actual ->

            val result = JsonNumberValueParser.read(StatefulCharReader(source))

            assertEquals(actual, result)
            assertEquals(Int::class, result::class)
        }

    }

    @Test
    fun doubleNumberTest() {

        val shouldSucceed = mapOf (
            "50.5" to 50.5,
            "-2.29" to -2.29,
            "0.0"   to 0.0
        )

        shouldSucceed.forEach { source, actual ->

            val result = JsonNumberValueParser.read(StatefulCharReader(source))

            assertEquals(actual, result)
            assertEquals(Double::class, result::class)
        }

    }

    @Test
    fun exponentNumberTest() {

        val shouldSucceed = mapOf (
            "255e7"  to 255e7,
            "255E7"  to 255e7,
            "255e+7" to 255e7,
            "255E+7" to 255e7,
            "255e-7" to 255e-7,
            "255E-7" to 255e-7
        )

        shouldSucceed.forEach { source, actual ->

            val result = JsonNumberValueParser.read(StatefulCharReader(source))

            assertEquals(actual, result)
            assertEquals(Double::class, result::class)
        }

    }

    @Test
    fun exponentDoubleNumberTest() {

        val shouldSucceed = mapOf (
            "255.25e7"  to 255.25e7,
            "255.56E7"  to 255.56e7,
            "255.75e+7" to 255.75e7,
            "255.96E+7" to 255.96e7,
            "255.13e-7" to 255.13e-7,
            "255.01E-7" to 255.01e-7
        )

        shouldSucceed.forEach { source, actual ->

            val result = JsonNumberValueParser.read(StatefulCharReader(source))

            assertEquals(actual, result)
            assertEquals(Double::class, result::class)
        }

    }

    @Test(expected = IllegalJsonNumberValueException::class)
    fun numberFailDoesNotMatchRegexTest() {
        val shouldFail  = arrayOf("12ee.", "1+e", "0-", "1+", "e", "0E", "1e123", "e7", "E7", "e-7", "E-7")
        shouldFail.forEach { JsonNumberValueParser.read(StatefulCharReader(it)) }
    }

    @Test(expected = IllegalJsonNumberTokenException::class)
    fun numberFailIllegalTokenTest() {
        val shouldFail  = arrayOf("12ae.", "e1nne", "0=", "1+", "e", "aE", "aaa")
        shouldFail.forEach { JsonNumberValueParser.read(StatefulCharReader(it)) }
    }

}