package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.exception.IllegalJsonAbsoluteValueException
import me.benjozork.kson.parser.JsonReader

import me.benjozork.kson.common.JsonToken

/**
 * Parses JSON boolean values
 *
 * @author Benjozork
 */
object JsonBooleanValueParser : Parser<Boolean>() {

    private const val LEGAL_CHARS = "afelrstu"

    override fun read(reader: JsonReader): Boolean {

        val booleanTrue = "true"
        val booleanFalse = "false"

        var currentString = ""

        val returnValue: Boolean

        readLoop@while(true) {

            if (JsonToken.isAbsoluteValueEnd(reader.currentChar)) {
                break@readLoop
            } else if (
                LEGAL_CHARS
                    .none {
                        it == reader.currentChar.toLowerCase()
                    }
            ) {
                throw IllegalJsonAbsoluteValueException(currentString, reader)
            } else {
                currentString += reader.currentChar
            }

            reader.read()

        }

        // We check the values

        val sanitizedString = currentString.toLowerCase()

        returnValue = when (sanitizedString) {
            booleanTrue -> true
            booleanFalse -> false
            else -> throw IllegalJsonAbsoluteValueException(currentString, reader)
        }

        return returnValue

    }

}