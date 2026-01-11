package io.github.kraftlin.command.paper

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.plugin.Plugin


public fun Plugin.registerKraftlinCommands(vararg commands: KraftlinPaperCommand) {
    lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
        val registrar = event.registrar()
        commands.forEach { command ->
            registrar.register(command.node, command.description, command.aliases)
        }
    }
}
