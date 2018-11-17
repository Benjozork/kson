package me.benjozork.kson.writer

import me.benjozork.kson.common.JsonToken

import me.benjozork.kson.writer.value.JsonValueWriter

object JsonObjectWriter : Writer<Map<String, Any?>>() {

    override fun print(writer: JsonWriter, data: Map<String, Any?>) {

        // Write array start

        writer.append(JsonToken.OBJECT_START)

        writer.addIndent()
        writer.newline()

        data.entries.forEachIndexed { index, entry ->

            JsonKeyWriter.print(writer, entry.key)

            writer.append(JsonToken.VALUE_ASSIGNMENT)
            writer.whitespace()

            JsonValueWriter.print(writer, entry.value)

            if (index != data.size - 1) {
                writer.append(JsonToken.ENTRY_SEPARATOR)
                writer.newline()
            } else {
                writer.remIndent()
                writer.newline()
                writer.append(JsonToken.OBJECT_END)
            }

        }

    }

}