package me.benjozork.kson.parser.exception

import me.benjozork.kson.common.exception.KsonException

class IllegalJsonBooleanValueException (
    actualValue: String
) : KsonException("unknown boolean value \"$actualValue\": expected \"true\" or \"false\"")