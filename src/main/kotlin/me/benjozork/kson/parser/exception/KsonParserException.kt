package me.benjozork.kson.parser.exception

import me.benjozork.kson.parser.JsonReader

import me.benjozork.kson.common.exception.KsonException

/**
 * Defines an error that occurs during JSON parsing
 *
 * @property message the error message, placed after the reader position
 * @property reader  the [JsonReader]
 *
 * @constructor generates an exception message containing the current reader position and the provided message
 *
 * @author Benjozork
 */
open class KsonParserException (
    message: String,
    val reader: JsonReader
) : KsonException("at position (${reader.position.line}, ${reader.position.col}), while in state \"${reader.ctx.currentState}\",\n\t${reader.currentLine}\n\t${" ".repeat(reader.position.col)}^--here\n$message")