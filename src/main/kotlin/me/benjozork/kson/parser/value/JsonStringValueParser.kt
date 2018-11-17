package me.benjozork.kson.parser.value

import me.benjozork.kson.parser.JsonKeyParser
import me.benjozork.kson.parser.Parser
import me.benjozork.kson.parser.JsonReader

/**
 * Parses JSON strings
 *
 * @author Benjozork
 */
object JsonStringValueParser : Parser<String>() {

    override fun read(reader: JsonReader): String {
        return JsonKeyParser.read(reader)
    }

}