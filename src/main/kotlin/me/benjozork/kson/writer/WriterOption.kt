package me.benjozork.kson.writer

import me.benjozork.kson.parser.ParserOptions

enum class WriterOption(val i: Int) {

    // Newline options

    NEWLINE_AFTER_OBJECT_START             (1),
    NEWLINE_AFTER_NESTED_OBJECT_START      (2),
    NEWLINE_AFTER_OBJECT_END               (4),
    NEWLINE_AFTER_NESTED_OBJECT_END        (8),

    NEWLINE_AFTER_ARRAY_START              (16),
    NEWLINE_AFTER_NESTED_ARRAY_START       (32),
    NEWLINE_AFTER_ARRAY_END                (64),
    NEWLINE_AFTER_NESTED_ARRAY_END         (128),

    NEWLINE_AFTER_KEY                      (256),

    NEWLINE_AFTER_VALUE_ASSIGNMENT         (512),
    NEWLINE_AFTER_VALUE                    (1_024),

    NEWLINE_AFTER_VALUE_SEPARATOR          (2_048),
    NEWLINE_AFTER_ARRAY_VALUE_SEPARATOR    (4_096),

    // Whitespace options

    WHITESPACE_AFTER_KEY                   (8_192),
    WHITESPACE_AFTER_VALUE_ASSIGNMENT      (16_384),
    WHITESPACE_AFTER_VALUE                 (32_768),
    WHITESPACE_AFTER_VALUE_SEPARATOR       (65_536),
    WHITESPACE_AFTER_ARRAY_VALUE_SEPARATOR (131_072),

    // This is used to represent an invalid option
    INVALID_OPTION                   (-1);

    companion object {

        fun resolve(optionsInt: Int): Set<ParserOptions> {

            val options = mutableSetOf<Int>()

            var tempOp: Int = optionsInt

            while (tempOp > 0) {
                val pow = highestPow2(tempOp)
                tempOp -= pow
                options.add(pow)
            }

            return options
                .map { powerOf2 ->
                    ParserOptions
                        .values()
                        .find { it.i == powerOf2 } ?: ParserOptions.INVALID_OPTION
                }
                .toSet()
        }

        private fun highestPow2(n: Int): Int {
            val p = (Math.log(n.toDouble()) / Math.log(2.0)).toInt()
            return Math.pow(2.0, p.toDouble()).toInt()
        }

    }

    /**
     * [ParserOptions] values annotated with this are not supported by the official JSON spec
     */
    @MustBeDocumented
    annotation class UnofficialSupport

}

enum class LineSeparator(val separator : String) {

    CR_LF ("\r\n"),
    LF    ("\n"),

    // System-dependent
    SYSTEM_DEPENDENT ("\uFFFF")

}