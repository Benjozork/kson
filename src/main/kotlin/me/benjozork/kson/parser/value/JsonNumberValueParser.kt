package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.Token
import me.benjozork.kson.parser.exception.IllegalJsonNumberValueException
import me.benjozork.kson.parser.exception.IllegalJsonNumberTokenException
import me.benjozork.kson.parser.internal.StatefulCharReader

/**
 * Parses a number in the JSON-legal number format
 *
 * @author Benjozork
 * @author elyatai
 */
object JsonNumberValueParser : Parser<Number>() {

    internal val LEGAL_CHARS = "Ee+-.0123456789".toCharArray()

    private val numberRegex = Regex("^(-?)(0|[1-9]\\d*)(?:.(\\d+))?(?:e([+-]?)(\\d+))?\$", RegexOption.IGNORE_CASE)
    private val doubleRegex = Regex("[.e]", RegexOption.IGNORE_CASE)

    /**
     * This is called to parse a JSON-legal number.
     *
     * @param reader the [StatefulCharReader] to read off of
     *
     * @return the parsed JSON-legal number
     */
    override fun read(reader: StatefulCharReader): Number {

        var currentString = ""

        readLoop@while(true) {

            if (reader.currentChar.isWhitespace()
                || reader.currentChar == '\n'
                || reader.currentChar == Token.ENTRY_SEPARATOR.char
                || reader.currentChar == Token.OBJECT_END.char
                || reader.currentChar == Token.ARRAY_END.char
                || reader.currentChar == 0xFFFF.toChar()) {

                break@readLoop

            } else if (
                    LEGAL_CHARS
                    .none {
                        it == reader.currentChar
                    }
            ) {
                throw IllegalJsonNumberTokenException(reader)
            }

            else currentString += reader.currentChar

            reader.read()

        }

        // Just for readability

        val finalString = currentString

        // Check if regexp matches

        if (!finalString.matches(numberRegex)) throw IllegalJsonNumberValueException(reader, finalString)

        val doubleMatcher = doubleRegex.toPattern().matcher(finalString)

        return when {
            doubleMatcher.find() -> finalString.toDouble()
            else -> finalString.toLong()
        }

    }

}