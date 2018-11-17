package me.benjozork.kson.parser.exception

import me.benjozork.kson.parser.internal.JsonReader

/**
 * Defines an error caused by an invalid JSON number.
 *
 * JSON numbers are checked against the following regex:
 *     ^(-?)(0|[1-9]\d*)(?:.(\d+))?(?:e([+-]?)(\d+))?$
 *
 * @constructor generates an exception message containing the current reader position, a message and the invalid number
 *
 * @author Benjozork
 */
class IllegalJsonNumberValueException (

    reader: JsonReader,
    actualValue: String

) : KsonParserException("illegal number value \"$actualValue\": does not match against regex", reader.position)