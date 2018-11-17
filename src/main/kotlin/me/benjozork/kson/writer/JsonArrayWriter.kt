package me.benjozork.kson.writer

import me.benjozork.kson.common.JsonToken

import me.benjozork.kson.writer.value.JsonValueWriter

object JsonArrayWriter : Writer<List<Any>>() {

    override fun print(writer: JsonWriter, data: List<Any>) {

        // Write array start

        writer.append(JsonToken.ARRAY_START)

        writer.addIndent()
        writer.newline()

        data.forEachIndexed { index, element ->

            JsonValueWriter.print(writer, element)

            if (index != data.size - 1) {
                writer.append(JsonToken.ENTRY_SEPARATOR)
                writer.newline()
            } else {
                writer.remIndent()
                writer.newline()
                writer.append(JsonToken.ARRAY_END)
            }

        }

    }

}