package me.benjozork.kson.parser.exception

import java.lang.Exception

class IllegalJsonNumberValueException (
    actualValue: String
) : Exception("illegal number value \"$actualValue\": does not match against regex")