package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.Token
import me.benjozork.kson.parser.exception.IllegalJsonAbsoluteValue
import me.benjozork.kson.parser.internal.StatefulCharReader

/**
 * Parses JSON boolean values
 *
 * @author Benjozork
 */
object JsonBooleanValueParser : Parser<Boolean>() {

    override fun read(reader: StatefulCharReader): Boolean {

        val booleanTrue = "true"
        val booleanFalse = "false"

        var currentString = ""

        val returnValue: Boolean

        readLoop@while(true) {

            // Is it the end of the value ?
            if (reader.currentChar == Token.WHITESPACE.char
                || reader.currentChar == Token.ENTRY_SEPARATOR.char
                || reader.currentChar == Token.OBJECT_END.char
                || reader.currentChar == Token.ARRAY_END.char) {
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