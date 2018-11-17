package me.benjozork.kson.writer.value

import me.benjozork.kson.writer.JsonArrayWriter
import me.benjozork.kson.writer.JsonObjectWriter
import me.benjozork.kson.writer.JsonWriter
import me.benjozork.kson.writer.NullableWriter

object JsonValueWriter : NullableWriter<Any>() {

    override fun print(writer: JsonWriter, data: Any?) {

        when (data) {

            null -> JsonNullValueWriter.print(writer, data)

            is Map<*, *> -> JsonObjectWriter.print(writer, data as Map<String, Any>)

            is List<*> -> JsonArrayWriter.print(writer, data as List<Any>)

            is String -> JsonStringValueWriter.print(writer, data)

            is Boolean -> JsonBooleanValueWriter.print(writer, data)

            is Number -> JsonNumberValueWriter.print(writer, data)

        }

    }

}