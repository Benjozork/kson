package me.benjozork.kson.parser

import me.benjozork.kson.parser.internal.StatefulCharReader

abstract class Parser<T> {

    abstract fun read(reader: StatefulCharReader) : T

}