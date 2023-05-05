package world.anhgelus.lemondedelanuit.botdelanuit.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData

abstract class BaseCommand {
    abstract val name: String
    /**
     * Generate the command for registration
     */
    abstract fun generateCommand(): CommandData

    /**
     * Handle the command
     *
     * @property event the event to handle
     */
    abstract fun handle(event: SlashCommandInteractionEvent)
}