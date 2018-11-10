package me.benjozork.kson.parser.model

class JsonObject (
    val data: MutableMap<String, Any>
) {

    fun getInt(key: String ): Int {

        try {
            return data[key] as Int
        } catch (e: TypeCastException) {
            throw IllegalStateException("kson: entry with key \"$key\" is not of requested type \"Int\", but rather of type \"${data[key]!!::class}\"")
        }

    }

    fun getDouble(key: String ): Double {

        try {
            return data[key] as Double
        } catch (e: TypeCastException) {
            throw IllegalStateException("kson: entry with key \"$key\" is not of requested type \"Double\", but rather of type \"${data[key]!!::class}\"")
        }

    }

    fun getObject(key: String ): JsonObject {

        try {
            return data[key] as JsonObject
        } catch (e: TypeCastException) {
            throw IllegalStateException("kson: entry with key \"$key\" is not of requested type \"JsonObject\", but rather of type \"${data[key]!!::class}\"")
        }

    }

    fun getArray(key: String ): JsonArray {

        try {
            return data[key] as JsonArray
        } catch (e: TypeCastException) {
            throw IllegalStateException("kson: entry with key \"$key\" is not of requested type \"JsonArray\", but rather of type \"${data[key]!!::class}\"")
        }

    }

}