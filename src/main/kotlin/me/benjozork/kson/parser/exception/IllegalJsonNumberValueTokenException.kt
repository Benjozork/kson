package me.benjozork.kson.parser.exception

import me.benjozork.kson.parser.value.JsonNumberValueParser
import java.lang.Exception

class IllegalJsonNumberValueTokenException (
    badToken: Char
) : Exception("unknown number token \'$badToken\': expected one of the following chars: ${JsonNumberValueParser.LEGAL_CHARS}")