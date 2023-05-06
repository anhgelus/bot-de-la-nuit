package world.anhgelus.lemondedelanuit.botdelanuit.commands

import net.dv8tion.jda.api.entities.Role
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

    /**
     * Check if the member have the right role
     *
     * @property event the event to check
     * @property role the role
     * @return true if the member have the role, false otherwise
     */
    protected fun checkRole(event: SlashCommandInteractionEvent, role: Role): Boolean {
        return event.member!!.roles.contains(role)
    }

    protected fun checkRole(event: SlashCommandInteractionEvent, role: Long): Boolean {
        return checkRole(event, event.guild!!.getRoleById(role)!!)
    }
}