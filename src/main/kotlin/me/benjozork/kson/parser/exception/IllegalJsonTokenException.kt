package me.benjozork.kson.parser.exception

import me.benjozork.kson.parser.Token

import java.lang.Exception

class IllegalJsonTokenException (
    val expectedToken: Token,
    val actualToken: Char
) : Exception("expected token ${expectedToken.name}, got \' $actualToken \' instead")