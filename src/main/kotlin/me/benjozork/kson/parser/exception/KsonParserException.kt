package me.benjozork.kson.parser.exception

import me.benjozork.kson.common.exception.KsonException
import me.benjozork.kson.parser.internal.ReaderPosition

open class KsonParserException(message: String, pos: ReaderPosition) : KsonException("at position (${pos.line}, ${pos.col}):\n $message")