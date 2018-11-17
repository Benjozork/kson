package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.exception.IllegalJsonNumberValueException
import me.benjozork.kson.parser.exception.IllegalJsonNumberTokenException
import me.benjozork.kson.parser.internal.JsonReader

import me.benjozork.kson.common.JsonToken

/**
 * Parses a number in the JSON-legal number format
 *
 * @author Benjozork
 * @author elyatai
 */
object JsonNumberValueParser : Parser<Number>() {

    private const val LEGAL_CHARS = "Ee+-.0123456789"

    private val numberRegex = Regex("^(-?)(0|[1-9]\\d*)(?:.(\\d+))?(?:e([+-]?)(\\d+))?\$", RegexOption.IGNORE_CASE)
    private val doubleRegex = Regex("[.e]", RegexOption.IGNORE_CASE)

    /**
     * This is called to parse a JSON-legal number.
     *
     * @param reader the [JsonReader] to read off of
     *
     * @return the parsed JSON-legal number
     */
    override fun read(reader: JsonReader): Number {

        var currentString = ""

        readLoop@while(true) {

            if (JsonToken.isAbsoluteValueEnd(reader.currentChar)) {
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
            else -> {
                val temp = finalString.toLong()
                if (temp < Int.MAX_VALUE) temp.toInt() else temp
            }
        }

    }

}