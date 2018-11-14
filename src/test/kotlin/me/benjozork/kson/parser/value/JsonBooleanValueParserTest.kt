package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.exception.IllegalJsonAbsoluteValue
import me.benjozork.kson.parser.internal.StatefulCharReader

import org.junit.Test

import org.junit.Assert.*

class JsonBooleanValueParserTest {

    @Test
    fun booleanTrueTest() {

        val sources = arrayOf("true", "True", "tRuE", "TRUE")

        sources.forEach { assertEquals(true, JsonBooleanValueParser.read(StatefulCharReader(it))) }

    }

    @Test
    fun booleanFalseTest() {

        val sources = arrayOf("false", "False", "fAlSe", "FALSE")

        sources.forEach { assertEquals(false, JsonBooleanValueParser.read(StatefulCharReader(it))) }

    }

    @Test(expected = IllegalJsonAbsoluteValue::class)
    fun booleanFailTest() {

        val sources = arrayOf("abcd", "489", "falsed", "null")

        sources.forEach { JsonBooleanValueParser.read(StatefulCharReader(it)) }

    }

}