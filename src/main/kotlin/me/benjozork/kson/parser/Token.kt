package me.benjozork.kson.parser

/**
 * A list of valid JSON tokens
 *
 * @property char the [Char] value representing this [Token]
 *
 * @author Benjozork
 */
enum class Token(val char: Char) {

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
    WHITESPACE           (' '),

    // Special token used in exception messages only
    NUMBER_TOKEN         ('\uFFFF');

}