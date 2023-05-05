package world.anhgelus.lemondedelanuit.botdelanuit.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands

object Verify : BaseCommand() {
    override val name = "verify"
    override fun generateCommand(): CommandData {
        return Commands.slash(name, "Vérifie un membre")
            .addOption(OptionType.USER, "user", "L'utilisateur à vérifier")
    }

    override fun handle(event: SlashCommandInteractionEvent) {
        TODO("Check if the user have the right role")
        TODO("Change the role of the registered user and send a welcome message")
    }
}