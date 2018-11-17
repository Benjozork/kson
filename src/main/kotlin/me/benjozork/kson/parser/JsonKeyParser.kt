package me.benjozork.kson.parser

import me.benjozork.kson.parser.internal.JsonReader

/**
 * Parses JSON entry keys. Not to be confused with [JsonValueParser]
 * which takes care of actual string literal values.
 *
 * @author Benjozork
 */
object JsonKeyParser : Parser<String>() {

    /**
     * This is called to parse a JSON entry key. Not to be confused with [JsonValueParser]
     * which takes care of actual string literal values.
     *
     * @param reader the [JsonReader] to read off of
     *
     * @return the parsed key
     */
    override fun read(reader: JsonReader): String {

        var returnedKey = ""

        var ignoreFirstQuote = true
        var escapeNextChar = false

        readLoop@while (true) {

            if (reader.currentChar == '\\') {
                escapeNextChar = true
            }

            if (reader.currentChar == '\"' && !ignoreFirstQuote && !escapeNextChar) {
                break@readLoop
            } else if (ignoreFirstQuote || (escapeNextChar && reader.currentChar == '\\')) {
                // All we need to do is to not go into the else branch.
            } else {
                returnedKey += reader.currentChar
            }

            if (reader.currentChar != '\\') {
                escapeNextChar = false
            }

            if (ignoreFirstQuote) ignoreFirstQuote = false

            reader.read()

        }

        reader.read() // Get out of the key and let the call site handle the next char

        return returnedKey

    }

}