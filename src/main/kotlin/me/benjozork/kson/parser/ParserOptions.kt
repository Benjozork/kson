package me.benjozork.kson.parser

enum class ParserOptions(val i: Int) {

    @UnofficialSupport ALLOW_TRAILING_COMMAS_IN_OBJECTS (1),
    @UnofficialSupport ALLOW_TRAILING_COMMAS_IN_ARRAYS  (2),

    @UnofficialSupport ALLOW_UNQUOTED_STRINGS           (4),
    @UnofficialSupport ALLOW_0_AND_1_AS_BOOL_VALUES     (8),

    @UnofficialSupport ALLOW_LINE_COMMENTS              (16),
    @UnofficialSupport ALLOW_BLOCK_COMMENTS             (32),

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