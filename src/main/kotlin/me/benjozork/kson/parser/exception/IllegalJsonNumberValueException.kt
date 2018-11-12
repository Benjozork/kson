package me.benjozork.kson.parser.exception

import me.benjozork.kson.common.exception.KsonException

class IllegalJsonNumberValueException (
    actualValue: String
) : KsonException("illegal number value \"$actualValue\": does not match against regex")