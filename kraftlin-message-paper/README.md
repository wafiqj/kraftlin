# Message

Declaration-style message builder for Kotlin on top of Kyori Adventure.

Kraftlin Message provides a small Kotlin DSL to build rich chat components in a structured, readable way.

This module only builds `Component` trees. Sending, serialization, and platform integration are handled by
platform-specific modules.

## Basic usage

```kotlin
val simpleMessage = message {
    text("Hello World!")
}
```

## Styling

```kotlin
val styled = message {
    text("Complicated ") {
        color(NamedTextColor.BLUE)
        strikeThrough()
        underlined()
        obfuscated()
        bold()
        italic()
    }
}
```

## Hover & click actions

```kotlin
val interactive = message {
    text("Hover me ") {
        hoverMessage {
            text("First part ") {
                color(NamedTextColor.GOLD)
                bold()
            }
            text("second part") {
                color(NamedTextColor.DARK_GRAY)
            }
        }
        runCommand("/say hello!")
    }
}
```

## Links and insertion

```kotlin
val links = message {
    text("with text formatting ") {
        underlined()
        openUrl("https://www.minecraft-asylum.de/")
    }
    text("and actions") {
        color(NamedTextColor.BLACK)
        insert("Fancy String")
        hoverMessage("Simple Text") {
            color(NamedTextColor.RED)
        }
    }
}
```
