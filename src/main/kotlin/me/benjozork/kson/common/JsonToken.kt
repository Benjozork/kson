package me.benjozork.kson.common

/**
 * A list of valid JSON tokens
 *
 * @property char the [Char] value representing this [JsonToken]
 *
 * @author Benjozork
 */
enum class JsonToken(val char: Char) {

    OBJECT_START         ('{'),
    OBJECT_END           ('}'),
    ARRAY_START          ('['),
    ARRAY_END            (']'),
    ENTRY_SEPARATOR      (','),
    VALUE_ASSIGNMENT     (':'),
    STRING_LITERAL_DELIM ('\"'),
    CHAR_LITERAL_DELIM   ('\''),
    NEGATIVE_SIGN        ('-'),
    POSITIVE_SIGN        ('+'),
    EXPONENT_SIGN        ('E'),

    // Special tokens used in exception messages only
    NUMBER_TOKEN         ('\uFFFF'),
    ABSOLUTE_VALUE_TOKEN ('\uFFFF'),

}