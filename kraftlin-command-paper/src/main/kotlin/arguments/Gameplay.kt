package io.github.kraftlin.command.paper.arguments

import io.github.kraftlin.command.argument
import io.github.kraftlin.command.paper.PaperArgumentNode
import io.github.kraftlin.command.paper.PaperContext
import io.github.kraftlin.command.paper.PaperLiteralNode
import io.papermc.paper.command.brigadier.argument.ArgumentTypes
import org.bukkit.GameMode
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot


public fun PaperLiteralNode.gameMode(
    name: String,
    block: PaperArgumentNode<GameMode>.() -> Unit,
): Unit = argument(name, ArgumentTypes.gameMode(), block)

public fun PaperContext.gameMode(name: String): GameMode = rawContext.getArgument<GameMode>(name, GameMode::class.java)


public fun PaperLiteralNode.objectiveCriteria(
    name: String,
    block: PaperArgumentNode<Criteria>.() -> Unit,
): Unit = argument(name, ArgumentTypes.objectiveCriteria(), block)

public fun PaperContext.objectiveCriteria(name: String): Criteria =
    rawContext.getArgument<Criteria>(name, Criteria::class.java)


public fun PaperLiteralNode.scoreboardDisplaySlot(
    name: String,
    block: PaperArgumentNode<DisplaySlot>.() -> Unit,
): Unit = argument(name, ArgumentTypes.scoreboardDisplaySlot(), block)

public fun PaperContext.scoreboardDisplaySlot(name: String): DisplaySlot =
    rawContext.getArgument<DisplaySlot>(name, DisplaySlot::class.java)
