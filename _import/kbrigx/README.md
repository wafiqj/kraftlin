# KBrigx

[![Build Status](https://github.com/kbrigx/kbrigx/actions/workflows/build.yml/badge.svg)](https://github.com/kbrigx/kbrigx/actions)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kbrigx/kbrigx-paper.svg?label=Maven%20Central)](https://central.sonatype.com/namespace/io.github.kbrigx)
[![PaperMC](https://img.shields.io/badge/paper-1.21%2B-blue.svg)](https://papermc.io)
[![Kotlin](https://img.shields.io/badge/kotlin-2.3.0-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A modern, type-safe Kotlin DSL for Brigadier commands — with first-class Paper support.

Build Minecraft commands that are readable, safe, and maintainable, without hiding how Brigadier or Paper
actually work.

## ✨ Why KBrigx?

Writing Brigadier commands directly is powerful — but also verbose, string-heavy, and error-prone.

### KBrigx:

```kotlin
val demoCommand = PaperKBrigx.command("demo") {
    player("target") {
        executes { sender, context ->
            val target = context.player("target")
            target.sendMessage("Hello")
            sender.sendMessage("Sent hello to ${target.name}")
        }
    }
}
```

### Brigadier + Paper:

> On Paper, Brigadier commands run on a `CommandSourceStack`, and player arguments are exposed as selector/resolver
> types that must be resolved against the source.

```kotlin
val demoCommand = Commands.literal("demo")
    .then(
        Commands.argument("target", ArgumentTypes.player())
            .executes { context ->
                val source = context.source
                val sender = source.sender
                val targetResolver = context.getArgument("target", PlayerSelectorArgumentResolver::class.java)
                val target = targetResolver.resolve(source).first()
                target.sendMessage("Hello")
                sender.sendMessage("Sent hello to ${target.name}")
                Command.SINGLE_SUCCESS
            }
    )
    .build()
```

KBrigx provides:

- a clean Kotlin DSL for building Brigadier command trees
- strong typing for arguments and context access
- built-in handling of Paper selector/resolver patterns
- thin, transparent wrappers — no hidden runtime magic
- focused helpers for common patterns, without hiding Brigadier or Paper APIs

## 📦 Modules

| Module | Description                                                           |
|--------|-----------------------------------------------------------------------|
| core   | Pure Brigadier + Kotlin DSL (no Minecraft or Paper dependencies)      |
| paper  | Paper-specific helpers: argument builders and typed context accessors |

## 🎯 Design goals

- expressive Kotlin DSL over Brigadier
- type safety where it matters (arguments, context values)
- minimal abstraction overhead
- easy to reason about, debug, and extend

## ⚙ Requirements

- Java 21+
- Kotlin 2+
- Paper 1.21+ for the `paper` module

## 📥 Installation

Artifacts are published on Maven Central.

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("io.github.kbrigx:kbrigx-paper:0.1.0")
}
```

### Maven

```xml

<dependency>
    <groupId>io.github.kbrigx</groupId>
    <artifactId>kbrigx-paper</artifactId>
    <version>0.1.0</version>
</dependency>
```

## 🔢 Versioning & Stability

This project follows semantic versioning.

**Status:** Early-stage (0.x). Minor versions may contain breaking changes while the API is refined. After 1.0.0, only major versions will contain breaking changes.

KBrigx is actively developed and used in production on our server across multiple plugins.

## 🚀 Usage

```kotlin
val command = PaperKBrigx.command("demo") {
    requiresPermission("demo.use")
    player("target") {
        executes { sender, context ->
            val target = context.player("target")
            target.sendMessage("Hello ${target.name}")
        }
    }
}
```

Paper helpers give you typed Player, World, etc. without casting or unsafe lookups.

### Registration

```kotlin
override fun onEnable() {
    registerBrigxCommands {
        command(
            node = command,
            description = "Demo command",
            aliases = listOf("d", "test")
        )
    }
}
```

## 🧠 Philosophy

KBrigx does not replace Brigadier or Paper — it wraps them.

It provides:

- a Kotlin DSL to build Brigadier command trees
- typed argument and context access
- a few focused helpers and runtime checks where compile-time guarantees are not possible

Under the hood, KBrigx builds standard Brigadier `CommandNode` trees.  
There is no reflection, code generation, or runtime proxying — just thin wrappers and structured builders.

## 🤝 Contributing

Contributions and ideas are welcome! Feel free to open issues or submit pull requests.

## 📄 License

Apache-2.0
