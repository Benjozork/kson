package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.*
import me.benjozork.kson.parser.exception.IllegalJsonTokenException

import me.benjozork.kson.common.JsonToken

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
     * @param reader the current [JsonReader]. This MUST currently be on the first char of the value that we want to parse
     *
     * @return the resulting value
     */
    override fun read(reader: JsonReader): Any {

        return when (reader.currentChar.toLowerCase()) {

            in NUMBER_TRIGGER_CHARS.toCharArray() -> {
                JsonNumberValueParser.read(reader)
            }

            in BOOLEAN_TRIGGER_CHARS.toCharArray() -> {
                JsonBooleanValueParser.read(reader)
            }

            JsonToken.OBJECT_START.char -> {
                JsonObjectParser.read(reader)
            }

            JsonToken.ARRAY_START.char -> {
                JsonArrayParser.read(reader)
            }

            JsonToken.STRING_LITERAL_DELIM.char -> {
                JsonStringValueParser.read(reader)
            }

            else -> {
                throw IllegalJsonTokenException(reader, JsonToken.OBJECT_START,
                                                             JsonToken.ARRAY_START,
                                                             JsonToken.STRING_LITERAL_DELIM,
                                                             JsonToken.NUMBER_TOKEN,
                                                             JsonToken.ABSOLUTE_VALUE_TOKEN)
            }
        }

    }

}