package me.benjozork.kson.writer

/**
 * Turns nullable objects of type [T] into JSON strings
 *
 * @param T the type of the objects to serialize
 *
 * @author Benjozork
 */
abstract class NullableWriter<T> {

    abstract fun print(writer: JsonWriter, data: T?)

}