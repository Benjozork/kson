package me.benjozork.kson.parser.internal

/**
 * Defines the current position of a [StatefulCharReader] inside of the [String] it is reading
 *
 * @property line  the current line number
 * @property col   the current column number
 * @property index the current index in the file
 *
 * @author Benjozork
 */
data class ReaderPosition (
    var line:  Int = 0,
    var col:   Int = 0,
    var index: Int = 0
)