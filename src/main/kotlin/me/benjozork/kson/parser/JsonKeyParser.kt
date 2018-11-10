package me.benjozork.kson.parser

import java.io.StringReader

object JsonKeyParser {

    /**
     *
     * @param reader StringReader
     * @param startChar Char
     *
     * @return String
     */
    fun readKey(reader: StringReader, startChar: Char): String {

        var readingComplete = false

        // The key to return
        var returnedKey = ""

        var currentChar = startChar
        var ignoreFirstQuote = true

        var escapeNextChar = false

        while (!readingComplete) {

            if (currentChar == '\\') {
                escapeNextChar = true
            }

            if (currentChar == '\"' && !ignoreFirstQuote && !escapeNextChar) {
                readingComplete = true
            } else if (ignoreFirstQuote) {
                // All we need to do is to not go into the else branch.
            } else {
                returnedKey += currentChar
            }

            if (ignoreFirstQuote) ignoreFirstQuote = false
            if (escapeNextChar) escapeNextChar = false

            currentChar = reader.read().toChar()

        }

        return returnedKey

    }

}