package me.benjozork.kson.parser.exception

import me.benjozork.kson.parser.Token
import me.benjozork.kson.parser.internal.ReaderPosition

/**
 * Defines an error caused by an invalid or misplaced token found in a JSON string
 *
 * @constructor generates an exception message containing the current reader position, a message, the expected tokens and
 * the tokens that were actually found
 *
 * @author Benjozork
 */
class IllegalJsonTokenException (

    vararg expectedTokens: Token,
              actualToken: Char,
                      pos: ReaderPosition

) : KsonParserException (generateMessage(expectedTokens, actualToken), pos)

fun generateMessage(expectedTokens: Array<out Token>, actualToken: Char): String {

    var message = ""

    if (expectedTokens.size > 1) {
        message += "expected one of the following tokens (${expectedTokens.joinToString(separator = ", ", transform = {it.name})})"
        message += " got '$actualToken' instead"
    } else {
        message += "expected token ${expectedTokens.first().name}, got '$actualToken' instead"
    }

    return message
}