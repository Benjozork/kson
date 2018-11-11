package me.benjozork.kson.parser

import me.benjozork.kson.parser.internal.StatefulCharReader
import me.benjozork.kson.parser.value.JsonBooleanValueParser
import me.benjozork.kson.parser.value.JsonNumberValueParser

object ParserTest {

    val testString = """
    {
        "test": "abcd",
        "test2": [
            "test",
            "test1",
            "testy"
        ]
    }
    """.trimIndent()

    fun parse() {

        /*val reader = StatefulCharReader(testString)

        reader.read()

        println(JsonObjectParser.read(reader, '}').data.toString())*/

        val parsedValueTest = JsonNumberValueParser.read(StatefulCharReader("1.25788e5"))

        println("value = $parsedValueTest: ${parsedValueTest::class}")

    }

}