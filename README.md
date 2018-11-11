# kson
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
    - ✔️ Parser infrastructure (`StatefulCharReader`, etc)
    - ✔️ `String` literal/key parser (kinda done)
    - ✔️ Number parser
    - 🚧 Object tree parser
    - 🚧 Array tree parser
- ❌ Object building (constructor-based, injection-based, custom, etc)
- ❌ Wrapping everything together

## Java interop
no
