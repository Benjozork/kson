package me.benjozork.kson.parser

import me.benjozork.kson.parser.internal.JsonReader
import org.junit.Test

import org.junit.Assert.*

class JsonKeyParserTest {

    @Test
    fun keyTest() {

        val source = mapOf(
            """"string"""" to """string""",
            """"test"""" to """test""",
            """"esc\"aped"""" to """esc"aped""",
            """"\"\"\""""" to """""""""
        )

        source.forEach { key, value -> assertEquals(value, JsonKeyParser.read(JsonReader(key))) }

    }

}