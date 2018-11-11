package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.internal.StatefulCharReader

object JsonStringValueParser : Parser<String>() {

    override fun read(reader: StatefulCharReader): String {
        return ""
    }

}