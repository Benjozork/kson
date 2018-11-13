package me.benjozork.kson.parser.internal

import java.io.StringReader

/**
 * This implements a [StringReader] that reads a string char by char and remembers what char was last read
 *
 * @property s the [String] to read off of
 *
 * @constructor create a new [StatefulCharReader] off of a [String] `s`
 *
 * @author Benjozork
 */
class StatefulCharReader(val s: String) {

    private var reader   = StringReader(s)

    val numLines = s.lines().size

    var currentChar: Char = reader.read().toChar()
        private set

    var position = ReaderPosition()
        private set

    private var doNewLine = false

    /**
     * Reads a char off the given [String]
     * @return the char that was read
     */
    fun read(): Char {
        position.index++
        position.col++

        if (doNewLine) {
            position.line++
            position.col = 0
        }

        val temp = reader.read().toChar()
        currentChar = temp

        // This works regardless of line separator
        if (currentChar == '\n') doNewLine = true

        return temp
    }

}