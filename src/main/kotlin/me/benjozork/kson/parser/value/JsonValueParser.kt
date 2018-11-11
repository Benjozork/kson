package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.internal.StatefulCharReader

/**
 * Reads JSON values like strings, numbers, arrays, booleans, nulls and objects
 *
 *
 */
object JsonValueParser : Parser<Any>() {

    private const val NUMBER_CHARS = "-0123456789"

    override fun read(reader: StatefulCharReader): Any {

       if (NUMBER_CHARS.toCharArray().any { reader.currentChar == it }) {
           // Parse a number
           val value = JsonNumberValueParser.read(reader)
       }

        return Any()

    }

}

