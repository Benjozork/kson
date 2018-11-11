package me.benjozork.kson.parser

import me.benjozork.kson.parser.internal.StatefulCharReader
import java.io.StringReader

/**
 * Parses JSON entry keys. Not to be confused with [JsonValueParser]
 * which takes care of actual string literal values.
 */
object JsonKeyParser : Parser<String>() {

    /**
     * This is called to parse a JSON entry key. Not to be confused with [JsonValueParser]
     * which takes care of actual string literal values.
     *
     * @param reader the [StringReader] to read off of
     *
     * @return the parsed key
     */
    override fun read(reader: StatefulCharReader): String {

        var currentChar = reader.currentChar()

        var returnedKey = ""

        var ignoreFirstQuote = true
        var escapeNextChar = false

        readLoop@while (true) {

            if (currentChar == '\\') {
                escapeNextChar = true
            }

            if (currentChar == '\"' && !ignoreFirstQuote && !escapeNextChar) {
                break@readLoop
            } else if (ignoreFirstQuote) {
                // All we need to do is to not go into the else branch.
            } else {
                returnedKey += currentChar
            }

            if (ignoreFirstQuote) ignoreFirstQuote = false
            if (escapeNextChar) escapeNextChar = false

            currentChar = reader.read()

        }

        reader.read() // Get out of the key and let the call site handle the next char

        return returnedKey

    }

}