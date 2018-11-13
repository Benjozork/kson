package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.internal.StatefulCharReader

/**
 * Parses JSON values like strings, numbers, arrays, booleans, nulls and objects
 *
 * @author Benjozork
 */
object JsonValueParser : Parser<Any>() {

    private const val NUMBER_TRIGGER_CHARS  = "-0123456789"
    private const val BOOLEAN_TRIGGER_CHARS = "tf"

    /**
     * Method that reads JSON values like strings, numbers, arrays, booleans, nulls and objects
     *
     * @param reader the current [StatefulCharReader]. This MUST currently be on the first char of the value that we want to parse
     *
     * @return the resulting value
     */
    override fun read(reader: StatefulCharReader): Any {

        val value: Any

        value = when (reader.currentChar.toLowerCase()) {

            in NUMBER_TRIGGER_CHARS.toCharArray() -> {
                JsonNumberValueParser.read(reader)
            }

            in BOOLEAN_TRIGGER_CHARS.toCharArray() -> {
                JsonBooleanValueParser.read(reader)
            }

            else -> {
                ""
            }

        }

        return value

    }

}