package me.benjozork.kson.parser

import me.benjozork.kson.parser.internal.JsonReader

/**
 * Parses object of type [T]
 *
 * @param T the type of the objects to parse
 *
 * @author Benjozork
 */
abstract class Parser<T> {

    abstract fun read(reader: JsonReader) : T

}