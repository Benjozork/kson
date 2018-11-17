package me.benjozork.kson.common

/**
 * Defines the current position of a [StatefulCharReader] inside of the [String] it is reading
 *
 * @property line  the current line number
 * @property col   the current column number
 * @property index the current index in the file
 *
 * @author Benjozork
 */
data class TextPosition (
    var line:  Int = 1,
    var col:   Int = 1,
    var index: Int = 0
)