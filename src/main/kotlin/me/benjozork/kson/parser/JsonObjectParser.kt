package me.benjozork.kson.parser

import me.benjozork.kson.parser.model.JsonObject

import java.lang.IllegalStateException

object JsonObjectParser : Parser<JsonObject>() {

    override fun read(reader: StatefulCharReader, startChar: Char): JsonObject {

        // Temp map and entry
        val tempMap = mutableMapOf<String, Any>()

        var tempKey = ""
        var tempValue: Any = ""

        // The current parsing state
        var currentState = ObjectState.WAITING_FOR_KEY

        // Is reading complete ?
        var readingComplete = false

        // The key to return
        var returnedObject: JsonObject

        // Current char being read
        var currentChar = startChar

        reader.read() // We already know the first char is {

        readLoop@while (!readingComplete) {

            // @TODO very temporary
            tempMap[tempKey] = tempValue

            if (currentChar == ' ') {
                // Skip whitespace
                currentChar = reader.read()
                continue@readLoop
            }

            when (currentState) {

                ObjectState.WAITING_FOR_KEY -> {

                    if (currentChar == '\"') { // Found a key, parse it

                        // It's important that this reader leaves this JsonKeyParser at position after the key, usually a colon
                        // UNQUOTED KEYS ARE NOT VALID JSON AND THEREFORE WILL NEVER BE ACCEPTED IN THIS PARSER.
                        val key = JsonKeyParser.read(reader, currentChar)

                        tempKey = key

                        // We update our currentChar according to the state of the reader. Usually currentChar should now contain a colon.
                        // That is, if the JSON is valid
                        currentChar = reader.currentChar()
                        currentState = ObjectState.WAITING_FOR_VALUE

                        continue@readLoop
                    }

                }

                ObjectState.WAITING_FOR_VALUE -> {

                    if (currentChar == ':') {
                        // Found a value, change state
                        currentState = ObjectState.FOUND_VALUE_WAITING_FOR_TRIGGER
                    } else {
                        // Whitespace is already ignored so we can else-check for other chars and throw an error
                        throw IllegalStateException("kson: expected VALUE_ASSIGNMENT but got: $currentChar")
                    }

                }

                ObjectState.FOUND_VALUE_WAITING_FOR_TRIGGER -> {

                        // We do not have to skip whitespace since it's already done

                        // Parse the value
                        //val value = JsonValueParser.read(reader, currentChar)
                        tempValue = JsonKeyParser.read(reader, currentChar) // TODO actual value

                        // Set the appropriate state, update our currentChar, and keep on parsing
                        currentChar = reader.currentChar()
                        currentState = ObjectState.WAITING_FOR_NEXT_OR_END
                        continue@readLoop
                }


                ObjectState.WAITING_FOR_NEXT_OR_END -> {

                    if (currentChar == Tokens.ENTRY_SEPARATOR.char) {

                        // We have found a comma, now wait for another key
                        tempKey = ""; tempValue = "" // We reset the temp key and value

                        // Set the appropriate state
                        currentState = ObjectState.WAITING_FOR_KEY

                    } else if (currentChar == Tokens.DATA_END.char) {

                        // We are done parsing the object
                        break@readLoop

                    } else {

                        // We have found an illegal character
                        throw IllegalStateException("kson: expected ENTRY_SEPARATOR or DATA_END but got: $currentChar")

                    }

                }
            }

            currentChar = reader.read()

        }

        return JsonObject(tempMap)

    }
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