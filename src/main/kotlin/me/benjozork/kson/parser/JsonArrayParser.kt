package me.benjozork.kson.parser

import me.benjozork.kson.parser.exception.IllegalJsonTokenException
import me.benjozork.kson.parser.internal.StatefulCharReader
import me.benjozork.kson.parser.value.JsonValueParser

/**
 * Parses JSON arrays and their values
 *
 * @author Benjozork
 */
object JsonArrayParser : Parser<List<Any>>() {

    /**
     * This is called to parse a JSON array.
     *
     * @param reader the [StatefulCharReader] to read off of
     *
     * @return the parsed array
     */
    override fun read(reader: StatefulCharReader): List<Any> {

        val returnValues = mutableListOf<Any>()

        var currentState = ArrayState.WAITING_FOR_FIRST_VALUE

        // The first char is always '['
        reader.read()

        readLoop@while(true) {

            if (reader.currentChar.isWhitespace() || reader.currentChar == '\n') {
                // Skip whitespace or newline
                reader.read()
                continue@readLoop
            }

            when (currentState) {

                ArrayState.WAITING_FOR_FIRST_VALUE -> {
                    // We do not have to skip whitespace or newlines since it's already done
                    // Parse the value

                    if (reader.currentChar == Token.ARRAY_END.char) { // Allow for empty arrays
                        break@readLoop
                    }

                    returnValues.add(JsonValueParser.read(reader))
                    currentState = ArrayState.WAITING_FOR_NEXT_OR_END
                    continue@readLoop
                }

                ArrayState.WAITING_FOR_VALUE -> {
                    // We do not have to skip whitespace or newlines since it's already done
                    // Parse the value
                    returnValues.add(JsonValueParser.read(reader))
                    currentState = ArrayState.WAITING_FOR_NEXT_OR_END
                    continue@readLoop
                }

                ArrayState.WAITING_FOR_NEXT_OR_END -> {

                    if (reader.currentChar == Token.ENTRY_SEPARATOR.char) {
                        currentState = ArrayState.WAITING_FOR_VALUE
                        reader.read() // This is necessary because we want the loop to process what is AFTER the comma
                        continue@readLoop
                    } else if (reader.currentChar == Token.ARRAY_END.char) {
                        // We are done parsing the object
                        break@readLoop
                    } else {
                        throw IllegalJsonTokenException(reader, Token.ENTRY_SEPARATOR, Token.ARRAY_END)
                    }

                }

            }

        }

        reader.read() // Leave the reader outside the array to not the calling parser

        return returnValues

    }

    enum class ArrayState {
        // The difference with the next one is that here we accept a closing bracket for an empty array
        WAITING_FOR_FIRST_VALUE,
        // We are waiting for a value
        WAITING_FOR_VALUE,
        // We are either waiting for another comma or the end of the object
        WAITING_FOR_NEXT_OR_END;
    }

}