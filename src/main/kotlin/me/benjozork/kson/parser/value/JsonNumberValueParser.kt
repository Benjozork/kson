package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.Token
import me.benjozork.kson.parser.exception.IllegalJsonNumberValueException
import me.benjozork.kson.parser.exception.IllegalJsonNumberValueTokenException
import me.benjozork.kson.parser.internal.StatefulCharReader

/**
 * Parses a number in the JSON-legal number format
 *
 * @author Benjozork
 * @author elyatai
 */
object JsonNumberValueParser : Parser<Number>() {

    const val LEGAL_CHARS = "Ee+-.0123456789"

    private val numberRegex = Regex("^(-?)(0|[1-9]\\d*)(?:.(\\d+))?(?:e([+-]?)(\\d+))?\$", RegexOption.IGNORE_CASE)

    /**
     * This is called to parse a JSON-legal number.
     *
     * @param reader the [StatefulCharReader] to read off of
     *
     * @return the parsed JSON-legal number
     */
    override fun read(reader: StatefulCharReader): Number {

        var currentString = ""

        val returnValue: Number

        readLoop@while(true) {

            if (reader.currentChar() == Token.WHITESPACE.char
                    || reader.currentChar() == Token.ENTRY_SEPARATOR.char
                    || reader.currentChar() == Token.OBJECT_END.char
                    || reader.currentChar() == Token.ARRAY_END.char
                    || reader.currentChar() == 0xFFFF.toChar()) {

                break@readLoop

            } else if (
                    LEGAL_CHARS
                    .toCharArray()
                    .none {
                        it == reader.currentChar()
                    }
            ) {

                throw IllegalJsonNumberValueTokenException(reader.currentChar())
            }

            else currentString += reader.currentChar()

            reader.read()

        }

        // Just for readability

        val finalString = currentString;

        // Let's actually check against the regex and then build the number data

        if (!currentString.matches(numberRegex)) {
            throw IllegalJsonNumberValueException(finalString)
        }

        val matcher = numberRegex.toPattern().matcher(finalString)
        matcher.find()

            // Sign (group 1)

        val sign = matcher.group(1)

            // Integer part (group 2)

        val intPart: Long = matcher.group(2).toLong()

            // Fraction part (group 3)

        val fracPart = try {
            matcher.group(3).toLong()
        } catch (e: Exception) {
            null
        }

            // Exponent part (group 5)

        val expPart = try {
            matcher.group(5).toLong()
        } catch (e: Exception) {
            null
        }

        return 0

    }

}