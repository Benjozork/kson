package me.benjozork.kson.writer.value

import me.benjozork.kson.writer.JsonArrayWriter
import me.benjozork.kson.writer.JsonWriter
import me.benjozork.kson.writer.Writer

object JsonValueWriter : Writer<Any>() {

    override fun print(writer: JsonWriter, data: Any) {

        when (data) {

            is Map<*, *> -> {

            }

            is List<*> -> {
                JsonArrayWriter.print(writer, data as List<Any>)
            }

            is String -> {
                JsonStringValueWriter.print(writer, data)
            }

        }

    }

}