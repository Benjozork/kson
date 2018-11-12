package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.internal.StatefulCharReader

/**
 * Reads JSON values like strings, numbers, arrays, booleans, nulls and objects
 *
 *
 */
object JsonValueParser : Parser<Any>() {

    private const val NUMBER_TRIGGER_CHARS  = "-0123456789"
    private const val BOOLEAN_TRIGGER_CHARS = "tf"

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