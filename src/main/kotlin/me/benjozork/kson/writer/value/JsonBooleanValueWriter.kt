package me.benjozork.kson.writer.value

import me.benjozork.kson.writer.JsonWriter
import me.benjozork.kson.writer.Writer

object JsonBooleanValueWriter : Writer<Boolean?>() {

    override fun print(writer: JsonWriter, data: Boolean?) {
        writer.append(data.toString())
    }

}