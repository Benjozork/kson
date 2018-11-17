package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.exception.IllegalJsonAbsoluteValueException
import me.benjozork.kson.parser.JsonReader

import org.junit.Test

import org.junit.Assert.*

class JsonBooleanValueParserTest {

    @Test
    fun booleanTrueTest() {

        val sources = arrayOf("true", "True", "tRuE", "TRUE")

        sources.forEach { assertEquals(true, JsonBooleanValueParser.read(JsonReader(it))) }

    }

    @Test
    fun booleanFalseTest() {

        val sources = arrayOf("false", "False", "fAlSe", "FALSE")

        sources.forEach { assertEquals(false, JsonBooleanValueParser.read(JsonReader(it))) }

    }

    @Test(expected = IllegalJsonAbsoluteValueException::class)
    fun booleanFailTest() {

        val sources = arrayOf("abcd", "489", "falsed", "null")

        sources.forEach { JsonBooleanValueParser.read(JsonReader(it)) }

    }

}