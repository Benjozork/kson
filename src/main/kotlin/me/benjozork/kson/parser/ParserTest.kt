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

    val arrayTest = """
        [
            0.5564e-5,.
            89789789,
            25e5,
            0.222e+77
        ]
    """.trimIndent()

    fun parse() {

        val reader = StatefulCharReader(arrayTest)

        val timeBefore = System.currentTimeMillis()
        println(JsonArrayParser.read(reader))
        println(System.currentTimeMillis() - timeBefore)

    }

}