package me.benjozork.kson

import me.benjozork.kson.parser.JsonObjectParser
import me.benjozork.kson.parser.internal.JsonReader

import me.benjozork.kson.writer.JsonObjectWriter
import me.benjozork.kson.writer.JsonWriter

fun main(args: Array<String>) {

    val bd = StringBuilder()

    val writer = JsonWriter(bd)

    JsonObjectWriter.print(writer,  JsonObjectParser.read(JsonReader("{\"test\": \"test2\", \"array\": [\"val1\", \"val2\", \"val3\"]}")))

    println(bd.toString())

}