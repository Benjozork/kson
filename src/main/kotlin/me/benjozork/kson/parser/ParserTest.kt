package me.benjozork.kson.parser

import me.benjozork.kson.parser.internal.StatefulCharReader

object ParserTest {

    val testString = """
    {
        "test": "abcd",
        "test2": [
            "test",d
            "test1",
            "testy"
        ]
    }
    """.trimIndent()

    fun parse() {

        val reader = StatefulCharReader(testString)

        reader.read()

        val timeBefore = System.currentTimeMillis()
        println(JsonObjectParser.read(reader).toString())
        println(System.currentTimeMillis() - timeBefore)

    }

}