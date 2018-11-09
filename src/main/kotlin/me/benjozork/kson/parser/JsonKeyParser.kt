package me.benjozork.kson.parser

import java.io.StringReader

object JsonKeyParser {

    fun readKey(reader: StringReader, startChar: Char): String {

        var readingComplete = false

        // The key to return
        var returnedKey = ""

        var currentChar = startChar

        while (!readingComplete) {

            //@TODO This does not handle escaped characters
            if (currentChar == '\"') {
                readingComplete = true
            } else {
                returnedKey += currentChar
            }

            currentChar = reader.read().toChar()

        }

        return returnedKey

    }

}