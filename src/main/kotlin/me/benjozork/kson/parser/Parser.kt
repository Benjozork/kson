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

        println(JsonKeyParser.readKey(StringReader(keyTest), 't'))

    }

    /*private fun read(reader: StringReader, startChar: Char) {

        var readingComplete = false

        val currentChar = reader.read().toChar()

        while (!readingComplete) {

            if (currentChar == '{') {
                val parsedObject = readObject(reader, startChar)
            }

        }

    }*/

    /*fun readObject(reader: StringReader, startChar: Char): JsonObject {

        // At first we are waiting for a key to arrive
        var objectState = ObjectState.WAITING_FOR_KEY

        var readingComplete = false

        val currentChar = startChar

        while (!readingComplete) {

            if (currentChar == '\"') {
                readKey(reader, startChar)
            } else {
                error("kson: was expecting TOKEN_ENTRY_KEY_START, but found \'$currentChar\'")
            }

        }

    }*/


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