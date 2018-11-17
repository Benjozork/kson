package me.benjozork.kson.writer

class KsonWritingContext (
    vararg writerOptions: WriterOption,
           lineSeparator: LineSeparator
) {

    val lineSeparator: LineSeparator = lineSeparator

    val writerOptions: Array<out WriterOption> = writerOptions

    fun hasOption(option: WriterOption) = writerOptions.contains(option)

}