package me.benjozork.kson.parser

import me.benjozork.kson.common.JsonToken

import me.benjozork.kson.parser.exception.IllegalJsonTokenException
import me.benjozork.kson.parser.internal.JsonReader
import me.benjozork.kson.parser.value.JsonValueParser

/**
 * Parses JSON objects with their keys and values
 *
 * @author Benjozork
 */
object JsonObjectParser : Parser<MutableMap<String, Any>>() {

    /**
     * This is called to parse a JSON object.
     *
     * @param reader the [JsonReader] to read off of
     *
     * @return the parsed object
     */
    override fun read(reader: JsonReader): HashMap<String, Any> {

        // Temp map and entry
        val tempMap = hashMapOf<String, Any>()

        var tempKey: String = ""
        var tempValue:  Any = ""

        // The current parsing state
        var currentState = ObjectState.WAITING_FOR_FIRST_KEY

        reader.read() // We already know the first char is {

        readLoop@while (true) {

            if (tempKey != "") tempMap[tempKey] = tempValue

            if (reader.currentChar.isWhitespace() || reader.currentChar == '\n') {
                // Skip whitespace or newline
                reader.read()
                continue@readLoop
            }

            when (currentState) {

                ObjectState.WAITING_FOR_FIRST_KEY -> {

                    if (reader.currentChar == JsonToken.STRING_LITERAL_DELIM.char) { // Found a key, parse it

                        // It's important that this reader leaves this JsonKeyParser at position after the key, usually a colon
                        // UNQUOTED KEYS ARE NOT VALID JSON AND THEREFORE WILL NEVER BE ACCEPTED IN THIS PARSER.
                        val key = JsonKeyParser.read(reader)

                        tempKey = key

                        currentState = ObjectState.WAITING_FOR_VALUE

                        continue@readLoop
                    } else if (reader.currentChar == JsonToken.OBJECT_END.char) {
                        break@readLoop
                    } else {
                        throw IllegalJsonTokenException(reader, JsonToken.STRING_LITERAL_DELIM)
                    }

                }

                ObjectState.WAITING_FOR_KEY -> {

                    if (reader.currentChar == JsonToken.STRING_LITERAL_DELIM.char) { // Found a key, parse it

                        // It's important that this reader leaves this JsonKeyParser at position after the key, usually a colon
                        // UNQUOTED KEYS ARE NOT VALID JSON AND THEREFORE WILL NEVER BE ACCEPTED IN THIS PARSER.
                        val key = JsonKeyParser.read(reader)

                        tempKey = key

                        currentState = ObjectState.WAITING_FOR_VALUE

                        continue@readLoop
                    } else {
                        throw IllegalJsonTokenException(reader, JsonToken.STRING_LITERAL_DELIM)
                    }

                }

                ObjectState.WAITING_FOR_VALUE -> {

                    if (reader.currentChar == JsonToken.VALUE_ASSIGNMENT.char) {
                        // Found a value, change state
                        currentState = ObjectState.FOUND_VALUE_WAITING_FOR_TRIGGER
                    } else {
                        // Whitespace is already ignored so we can else-check for other chars and throw an error
                        throw IllegalJsonTokenException(reader, JsonToken.VALUE_ASSIGNMENT)
                    }

                }

                ObjectState.FOUND_VALUE_WAITING_FOR_TRIGGER -> {

                        // We do not have to skip whitespace or newlines since it's already done

                        // Parse the value
                        //val value = JsonValueParser.read(reader, reader.currentChar)
                        tempValue = JsonValueParser.read(reader) // TODO actual value

                        currentState = ObjectState.WAITING_FOR_NEXT_OR_END
                        continue@readLoop
                }


                ObjectState.WAITING_FOR_NEXT_OR_END -> {

                    if (reader.currentChar == JsonToken.ENTRY_SEPARATOR.char) {
                        // We have found a comma, now wait for another key
                        tempKey = ""; tempValue = "" // We reset the temp key and value
                        currentState = ObjectState.WAITING_FOR_KEY
                    } else if (reader.currentChar == JsonToken.OBJECT_END.char) {
                        // We are done parsing the object
                        break@readLoop
                    } else {
                        throw IllegalJsonTokenException(reader, JsonToken.ENTRY_SEPARATOR, JsonToken.OBJECT_END)
                    }

                }
            }

            reader.read()

        }

        return tempMap

    }

    enum class ObjectState {
        // The difference with the next one is that here we accept a closing bracket for an empty object
        WAITING_FOR_FIRST_KEY,
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