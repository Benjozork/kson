package me.benjozork.kson.parser.exception

import me.benjozork.kson.common.exception.KsonException
import me.benjozork.kson.parser.internal.ReaderPosition

/**
 * Defines an error that occurs during JSON parsing
 *
 * @property message the error message, placed after the reader position
 * @property pos     the reader position, placed before the error message
 *
 * @constructor generates an exception message containing the current reader position and the provided message
 *
 * @author Benjozork
 */
open class KsonParserException(override val message: String, val pos: ReaderPosition) : KsonException("at position (${pos.line}, ${pos.col}):\n $message")