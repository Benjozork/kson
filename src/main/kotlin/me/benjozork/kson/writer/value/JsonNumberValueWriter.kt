package me.benjozork.kson.writer.value

import me.benjozork.kson.writer.JsonWriter
import me.benjozork.kson.writer.Writer

object JsonNumberValueWriter : Writer<Number>() {

    override fun print(writer: JsonWriter, data: Number) {
        writer.append(data.toString())
    }

}