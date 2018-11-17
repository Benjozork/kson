# kson ![Packagist](https://img.shields.io/packagist/l/doctrine/orm.svg?style=popout-square)
a `json` parser with kotlin support, including support for nullable/non-nullable types and more.

## Why
I wanted to use `gson` but it doesn't support kotlin nullability and sets everything to `null` like a caveman

## Features
- (WIP) no unnecessary wrappers, returns primitive types and objects (not for now, I need wrappers for prototyping)
- (WIP) supports kotlin's nullability system
- (WIP) uses annotations to customize parsing
- (WIP) support for custom parsers

## Roadmap & todo
- 🚧 Basic parsers (literal values, object/array trees)
    - 🚧 Parser infrastructure (`JsonReader`, etc)
    - ✔️ `String` literal/key parser
    - ✔️ Number parser
    - ✔️ Object tree parser
    - ✔️ Array tree parser
- 🚧 Basic writers
    - 🚧 Writer infrastructure (`JsonWriter`, etc)
    - 🚧 `String` literal/key serializer
    - ❌ Number serializer
    - 🚧 Object tree serializer
    - 🚧 Array tree serializer
- ❌ Object building (constructor-based, injection-based, custom, etc)
- ❌ Wrapping everything together

## Java interop
no
