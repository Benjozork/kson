package me.benjozork.kson.parser

object JsonValueParser : Parser<Any>() {

    override fun read(reader: StatefulCharReader, startChar: Char): Any {
        return Any()
    }

}
