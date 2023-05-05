package world.anhgelus.lemondedelanuit.botdelanuit.utils

import net.dv8tion.jda.api.entities.Guild
import world.anhgelus.lemondedelanuit.botdelanuit.commands.BaseCommand

object Commands {
    fun createCommands(guild: Guild, commands: Map<String, BaseCommand>) {
        commands.forEach{
            guild.updateCommands().addCommands(
                it.value.generateCommand()
            ).queue()
        }
    }
}