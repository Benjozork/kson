package me.benjozork.kson.parser

class JsonParserContext {

    val options: MutableMap<ParserOptions, Boolean> = mutableMapOf()

    var currentState: String
        private set

    init {
        // Set options to default values
        ParserOptions.values().forEach { options[it] = it.defaultValue }

        // Set default state
        currentState = "NOT_STARTED"
    }

    fun setCurrentState(state: JsonObjectParser.ObjectState) {
        currentState = "OBJECT / ${state.name}"
    }

    fun setCurrentState(state: JsonArrayParser.ArrayState) {
        currentState = "ARRAY / ${state.name}"
    }

}