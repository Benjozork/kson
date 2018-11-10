package me.benjozork.kson.parser

enum class Tokens(val char: Char) {

    DATA_START('{'),
    DATA_END('}'),
    ARRAY_START('['),
    ARRAY_END(']'),
    ENTRY_SEPARATOR(','),
    VALUE_ASSIGNMENT(':'),
    STRING_LITERAL_DELIM('\"'),
    CHAR_LITERAL_DELIM('\'');

}