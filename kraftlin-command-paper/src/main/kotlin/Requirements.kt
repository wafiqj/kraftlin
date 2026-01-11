package io.github.kraftlin.command.paper

import io.github.kraftlin.command.requires
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

public fun PaperLiteralNode.requiresPermission(permission: String): Unit =
    requires { it.sender.hasPermission(permission) }

public fun PaperLiteralNode.requiresPlayer(): Unit =
    requires { it.sender is Player }

public fun PaperLiteralNode.requiresSender(predicate: (CommandSender) -> Boolean): Unit =
    requires { predicate(it.sender) }
