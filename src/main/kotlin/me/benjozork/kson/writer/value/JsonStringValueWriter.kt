package me.benjozork.kson.writer.value

import me.benjozork.kson.common.JsonToken

import me.benjozork.kson.writer.JsonWriter
import me.benjozork.kson.writer.Writer

object JsonStringValueWriter : Writer<String>() {

    override fun print(writer: JsonWriter, data: String) {

        var finalString = "" + data // Make sure we copy the string

        finalString.replace("\"", "\\\"")

        writer.append(JsonToken.STRING_LITERAL_DELIM)
        writer.append(finalString)
        writer.append(JsonToken.STRING_LITERAL_DELIM)

    }

}