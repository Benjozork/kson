package me.benjozork.kson.parser.exception

import java.lang.Exception

class IllegalJsonBooleanValueException (
    actualValue: String
) : Exception("unknown boolean value \"$actualValue\": expected \"true\" or \"false\"")