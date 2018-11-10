package me.benjozork.kson.parser

import java.io.StringReader

/**
 * Parses JSON entry keys. Not to be confused with [JsonValueParser]
 * which takes care of actual string literal values.
 */
object JsonKeyParser {

    /**
     * This is called to parse a JSON entry key. Not to be confused with [JsonValueParser]
     * which takes care of actual string literal values.
     *
     * @param reader    the [StringReader] to read off of
     * @param startChar the char being read at the moment this method was called
     *
     * @return the parsed key
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