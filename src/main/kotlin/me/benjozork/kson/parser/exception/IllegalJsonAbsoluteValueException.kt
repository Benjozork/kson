package me.benjozork.kson.parser.exception

import me.benjozork.kson.parser.internal.JsonReader

import me.benjozork.kson.parser.value.JsonValueParser

/**
 * Defines an error caused by an invalid absolute value. This will occur if the first letter of an unquoted
 * string value happens to be 't', 'f' or 'n', while the rest of the string is not `equalsIgnoreCase` to `true`, false`
 * or `null`. This somewhat odd and specific behavior is caused by the fact that [JsonValueParser] will try to parse
 * boolean/null values if the first char it comes across is 't', 'f', or 'n'.
 *
 * @author Benjozork
 */
class IllegalJsonAbsoluteValueException (
    actualValue: String,
         reader: JsonReader
) : KsonParserException("unknown absolute value \"$actualValue\": expected \"true\", \"false\" or a number", reader.position)