package me.benjozork.kson.writer.value

import me.benjozork.kson.writer.JsonWriter
import me.benjozork.kson.writer.NullableWriter

object JsonNullValueWriter : NullableWriter<String>() {

    override fun print(writer: JsonWriter, data: String?) {
        writer.append("null")
    }

}