package me.benjozork.kson.writer

import me.benjozork.kson.writer.value.JsonStringValueWriter

object JsonKeyWriter : Writer<String>() {

    override fun print(writer: JsonWriter, data: String) {
        JsonStringValueWriter.print(writer, data)
    }

}