package me.benjozork.kson.parser

import me.benjozork.kson.parser.exception.IllegalJsonTokenException
import me.benjozork.kson.parser.internal.StatefulCharReader
import me.benjozork.kson.parser.model.JsonObject
import me.benjozork.kson.parser.value.JsonValueParser

object JsonObjectParser : Parser<JsonObject>() {

    override fun read(reader: StatefulCharReader): JsonObject {

        // Temp map and entry
        val tempMap = mutableMapOf<String, Any>()

        var tempKey = ""
        var tempValue: Any = ""

        // The current parsing state
        var currentState = ObjectState.WAITING_FOR_KEY

        // The key to return
        var returnedObject: JsonObject

        reader.read() // We already know the first char is {

        readLoop@while (true) {

            // @TODO very temporary
            tempMap[tempKey] = tempValue

            if (reader.currentChar == ' ') {
                // Skip whitespace
                reader.read()
                continue@readLoop
            }

            when (currentState) {

                ObjectState.WAITING_FOR_KEY -> {

                    if (reader.currentChar == Token.STRING_LITERAL_DELIM.char) { // Found a key, parse it

                        // It's important that this reader leaves this JsonKeyParser at position after the key, usually a colon
                        // UNQUOTED KEYS ARE NOT VALID JSON AND THEREFORE WILL NEVER BE ACCEPTED IN THIS PARSER.
                        val key = JsonKeyParser.read(reader)

                        tempKey = key

                        currentState = ObjectState.WAITING_FOR_VALUE

                        continue@readLoop
                    }

                }

                ObjectState.WAITING_FOR_VALUE -> {

                    if (reader.currentChar == Token.VALUE_ASSIGNMENT.char) {
                        // Found a value, change state
                        currentState = ObjectState.FOUND_VALUE_WAITING_FOR_TRIGGER
                    } else {
                        // Whitespace is already ignored so we can else-check for other chars and throw an error
                        throw IllegalJsonTokenException(Token.VALUE_ASSIGNMENT, actualToken = reader.currentChar, pos = reader.position)
                    }

                }

                ObjectState.FOUND_VALUE_WAITING_FOR_TRIGGER -> {

                        // We do not have to skip whitespace since it's already done

                        // Parse the value
                        //val value = JsonValueParser.read(reader, reader.currentChar)
                        tempValue = JsonValueParser.read(reader) // TODO actual value

                        currentState = ObjectState.WAITING_FOR_NEXT_OR_END
                        continue@readLoop
                }


                ObjectState.WAITING_FOR_NEXT_OR_END -> {

                    if (reader.currentChar == Token.ENTRY_SEPARATOR.char) {

                        // We have found a comma, now wait for another key
                        tempKey = ""; tempValue = "" // We reset the temp key and value

                        // Set the appropriate state
                        currentState = ObjectState.WAITING_FOR_KEY

                    } else if (reader.currentChar == Token.OBJECT_END.char) {

                        // We are done parsing the object
                        break@readLoop

                    } else {

                        // We have found an illegal character
                        throw IllegalJsonTokenException(Token.ENTRY_SEPARATOR, Token.OBJECT_END, actualToken = reader.currentChar, pos = reader.position)

                    }

                }
            }

            reader.read()

        }

        return JsonObject(tempMap)

    }

    enum class ObjectState {
        // Waiting for a key to start
        WAITING_FOR_KEY,
        // We are done parsing the key and we are now waiting for a colon (':)
        WAITING_FOR_VALUE,
        // We have found a colon (':'), however we are waiting for a value to start
        FOUND_VALUE_WAITING_FOR_TRIGGER,
        // We are either waiting for another comma or the end of the object
        WAITING_FOR_NEXT_OR_END;

    }

}