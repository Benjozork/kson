package me.benjozork.kson.parser

import org.junit.Test

import org.junit.Assert.*

class JsonArrayParserTest {

    @Test
    fun arrayTest() {

        val source =
        """
        [
            "abcd",
            "efgh",
            "ijkl",
            "mnop",
            29,
            2.35e-9,
            true,
            false
        ]
        """.trimIndent()

        val sourceSingleLine = """["abcd","efgh","ijkl","mnop",29,2.35e-9,true,false]"""

        val sourceEmpty = """[]"""

        val expectedArray = listOf("abcd", "efgh", "ijkl", "mnop", 29, 2.35e-9, true, false)

        assertEquals(expectedArray, JsonArrayParser.read(JsonReader(source)))
        assertEquals(expectedArray, JsonArrayParser.read(JsonReader(sourceSingleLine)))
        assertEquals(listOf<Any>(), JsonArrayParser.read(JsonReader(sourceEmpty)))

    }

}