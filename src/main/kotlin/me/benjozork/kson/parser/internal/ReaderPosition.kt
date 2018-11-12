package me.benjozork.kson.parser.internal

data class ReaderPosition (
    var line:  Int = 0,
    var col:   Int = 0,
    var index: Int = 0
)