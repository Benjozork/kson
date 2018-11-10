package me.benjozork.kson.parser

object ParserTest {

    val testString = """
    {
        "test": "abcd",
        "test2": [
            "test",
            "test1",
            "testy"
        ]
    }
    """.trimIndent()

    fun parse() {

        val reader = StatefulCharReader(testString)

        reader.read()

        println(JsonObjectParser.read(reader, '}').data.toString())

    }

}