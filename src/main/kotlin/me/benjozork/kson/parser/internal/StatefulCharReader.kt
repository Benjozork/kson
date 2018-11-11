package me.benjozork.kson.parser.internal

import java.io.StringReader

/**
 * This implements a [StringReader] that reads a string char by char and remembers what char was last read
 *
 * @property s the [String] to read off of
 *
 * @constructor create a new [StatefulCharReader] off of a [String] `s`
 */
class StatefulCharReader(val s: String) {

    private var reader: StringReader = StringReader(s)

    var currentChar: Char = reader.read().toChar()
        private set

    /**
     * Reads a char off the given [String]
     * @return the char that was read
     */
    fun read(): Char {
        val temp = reader.read().toChar()
        currentChar = temp
        return temp
    }

}