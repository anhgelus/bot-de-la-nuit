package world.anhgelus.lemondedelanuit.botdelanuit.event

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import world.anhgelus.lemondedelanuit.botdelanuit.commands.BaseCommand

class SlashCommands(private val commands: Map<String, BaseCommand>): ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        this.commands.forEach{ (name, command) ->
            if (event.name == name) {
                command.handle(event)
            }
        }
    }
}