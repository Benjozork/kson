package me.benjozork.kson.parser.exception

import me.benjozork.kson.parser.Token

import java.lang.Exception

class IllegalJsonTokenException (
    vararg expectedTokens: Token,
    actualToken: Char
) : Exception (
        "expected${if (expectedTokens.size > 1) " one of the following " else " "}token${if (expectedTokens.size > 1) "s (" else " "}"
        + expectedTokens.joinToString(separator = ", ", transform = { it.name }) + (if (expectedTokens.size > 1) ")" else "")
        + ", got \'$actualToken\' instead"
    )

