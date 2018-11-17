package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.common.JsonToken
import me.benjozork.kson.parser.exception.IllegalJsonAbsoluteValue
import me.benjozork.kson.parser.internal.JsonReader

/**
 * Parses JSON boolean values
 *
 * @author Benjozork
 */
object JsonBooleanValueParser : Parser<Boolean>() {

    override fun read(reader: JsonReader): Boolean {

        val booleanTrue = "true"
        val booleanFalse = "false"

        var currentString = ""

        val returnValue: Boolean

        readLoop@while(true) {

            // Is it the end of the value ?
            if (reader.currentChar.isWhitespace()
                || reader.currentChar == '\n'
                || reader.currentChar == JsonToken.ENTRY_SEPARATOR.char
                || reader.currentChar == JsonToken.OBJECT_END.char
                || reader.currentChar == JsonToken.ARRAY_END.char
                || reader.currentChar == (-1).toChar()) {
                break@readLoop
            // If not, we add the current char to the string and go to the next one
            } else {
                currentString += reader.currentChar
            }

            // Go foward
            reader.read()

        }

        // We check the values

        val sanitizedString = currentString.toLowerCase()

        returnValue = when (sanitizedString) {
            booleanTrue -> true
            booleanFalse -> false
            else -> throw IllegalJsonAbsoluteValue(currentString)
        }

        return returnValue

    }

}