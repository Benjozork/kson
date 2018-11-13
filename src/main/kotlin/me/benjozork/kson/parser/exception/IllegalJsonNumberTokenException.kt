package me.benjozork.kson.parser.exception

import me.benjozork.kson.common.exception.KsonException

/**
 * Defines an error caused by an invalid or misplaced number token found in a JSON number
 *
 * @constructor generates an exception message containing the current reader position, a message and the invalid token
 *
 * @author Benjozork
 */
class IllegalJsonNumberTokenException (
    badToken: Char
) : KsonException("unknown number token \'$badToken\': expected a valid JSON number token. See JSON spec for detail")