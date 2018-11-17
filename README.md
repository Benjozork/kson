<img src="https://i.imgur.com/m7mOSpS.png" width="300px" height="65px" />

# kson ![Packagist](https://img.shields.io/packagist/l/doctrine/orm.svg?style=popout-square)
a `json` parser with kotlin support, including support for nullable/non-nullable types and more.

## Why
I wanted to use `gson` but it doesn't support kotlin nullability and sets everything to `null` like a caveman

## Features
- no unnecessary wrappers, returns primitive types and objects
- (WIP) supports kotlin's nullability system
- (WIP) uses annotations to customize parsing
- (WIP) support for custom parsers

## Progress
I'm currently working on the bare minimum code, so nothing is pretty, well done or even readable.  
If anyone sees this and is willing to help, feel free to open an issue or PR!

**Here's what works for now:**  
    - Basic parsing: map/array composition, number/bool/string read  
    - Basic writing: (map/array serialize, number/bool/string serialize)  

**Here's what's planned in the near future**:  
    - Tidying up the code in `parser`  
    - Adding missing functionality to `writer`
    
**Here's what's planned after that**:  
    - Adding complementary functionality to `parser`/`reader` (format tolerance options, printing options (raw, prettyprint, etc))  
    - Object composition (constructor, inject, type mapping with annotations to reduce the need for custom serializers)  
    - JSON schema validation  

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
