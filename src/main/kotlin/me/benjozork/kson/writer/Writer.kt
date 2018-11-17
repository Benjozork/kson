package me.benjozork.kson.writer

/**
 * Turns objects of type [T] into JSON strings
 *
 * @param T the type of the objects to serialize
 *
 * @author Benjozork
 */
abstract class Writer<T> {

    abstract fun print(writer: JsonWriter, data: T)

}