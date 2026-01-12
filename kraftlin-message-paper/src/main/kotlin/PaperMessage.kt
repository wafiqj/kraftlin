package io.github.kraftlin.message.paper

import io.github.kraftlin.message.ClickableMessage
import net.kyori.adventure.text.format.TextColor
import org.bukkit.command.CommandSender


public fun CommandSender.message(block: ClickableMessage.() -> Unit): Unit =
    sendMessage(io.github.kraftlin.message.message(block))

public fun CommandSender.message(text: String, color: TextColor? = null) {
    if (color == null) {
        sendMessage(io.github.kraftlin.message.message(text))
    } else {
        sendMessage(io.github.kraftlin.message.message(text, color))
    }
}
