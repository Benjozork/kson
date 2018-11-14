package me.benjozork.kson.parser

import me.benjozork.kson.parser.exception.IllegalJsonTokenException
import me.benjozork.kson.parser.internal.StatefulCharReader

import org.junit.Test

import org.junit.Assert.*

class JsonObjectParserTest {

    @Test
    fun objectTest() {

        val source =
        """
        {
            "abcd": "efgh",
            "ijkl": [
                "mnop",
                "qrst",
                "uvxy"
            ],
            "z": 0.215e-9
        }
        """.trimIndent()

        val sourceSingleLine = """{"abcd":"efgh","ijkl": ["mnop","qrst","uvxy"],"z": 0.215e-9}"""

        val sourceEmpty = """{}"""

        val expectedObject = mapOf (
            "abcd" to "efgh",
            "ijkl" to listOf(
                "mnop",
                "qrst",
                "uvxy"
            ),
            "z" to 0.215e-9
        )

        assertEquals(expectedObject, JsonObjectParser.read(StatefulCharReader(source)))
        assertEquals(expectedObject, JsonObjectParser.read(StatefulCharReader(sourceSingleLine)))
        assertEquals(mapOf<String, Any>(), JsonObjectParser.read(StatefulCharReader(sourceEmpty)))

    }

    @Test(expected = IllegalJsonTokenException::class)
    fun objectFailTrailingCommaTest() {

        val source =
        """
        {
            "abcd": "efgh",
        }
        """.trimIndent()

        JsonObjectParser.read(StatefulCharReader(source))
    }

    @Test(expected = IllegalJsonTokenException::class)
    fun objectFailPrematureEndTest() {

        val source =
        """
        {
            "abcd":
        }
        """.trimIndent()

        JsonObjectParser.read(StatefulCharReader(source))
    }

    @Test(expected = IllegalJsonTokenException::class)
    fun objectFailIllegalTokenTest() {

        val source =
            """
        {
            "abcd":]
        }
        """.trimIndent()

        JsonObjectParser.read(StatefulCharReader(source))
    }

}