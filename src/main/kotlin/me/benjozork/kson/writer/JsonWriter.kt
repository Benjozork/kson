package me.benjozork.kson.writer

import me.benjozork.kson.common.JsonToken
import me.benjozork.kson.common.TextPosition

class JsonWriter(private val builder: StringBuilder) {

    var position = TextPosition()
        private set

    private var doNewLine = false

    private var indentLevel = 0

    fun append(s: String) {
        builder.append(s)
        updatePos(s)
    }

    fun append(token: JsonToken) {
        builder.append(token.char)
        updatePos(token.char.toString())
    }

    fun addIndent() = indentLevel++

    fun remIndent() = indentLevel--

    fun newline() = append("\n${"    ".repeat(indentLevel)}")

    fun whitespace() = append(" ")

    private fun updatePos(appended: String) {

        for (currChar in appended) {

            position.index++
            position.col++

            if (doNewLine) {
                position.line++
                position.col = 0
                doNewLine = false
            }

            // This works regardless of line separator
            if (currChar == '\n') doNewLine = true

        }
    }

}