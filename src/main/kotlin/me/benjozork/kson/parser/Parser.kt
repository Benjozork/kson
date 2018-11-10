package me.benjozork.kson.parser

import java.io.StringReader

object Parser {

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

        val keyTest = """
            "test"
        """.trimIndent()

        val reader = StringReader(keyTest)

        reader.read()

        println(JsonKeyParser.readKey(reader, '\"'))

    }


    enum class ObjectState {
        WAITING_FOR_KEY,
            // Parsing the key
        PARSING_KEY,
            // Waiting for a value to start
        WAITING_FOR_VALUE,
            // Parsing the value
        PARSING_VALUE,
        // We are done with parsing the last entry (no comma found after value) and we are waiting for the last "}" charcter.
        // This is optional but must send out a warning
        WAITING_FOR_END
    }

}