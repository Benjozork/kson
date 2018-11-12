package me.benjozork.kson.parser.exception

import me.benjozork.kson.common.exception.KsonException

class IllegalJsonNumberTokenException (
    badToken: Char
) : KsonException("unknown number token \'$badToken\': expected a valid JSON number token. See JSON spec for detail")