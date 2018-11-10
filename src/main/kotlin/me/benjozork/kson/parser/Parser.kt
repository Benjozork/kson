package me.benjozork.kson.parser

abstract class Parser<T> {

    abstract fun read(reader: StatefulCharReader, startChar: Char) : T

}