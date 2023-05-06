package world.anhgelus.lemondedelanuit.botdelanuit.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import world.anhgelus.lemondedelanuit.botdelanuit.utils.SO
import java.util.*

object Verify : BaseCommand() {
    override val name = "verify"
    override fun generateCommand(): CommandData {
        return Commands.slash(name, "Verify a user")
            .addOption(OptionType.USER, "user", "The user to verify")
    }

    override fun handle(event: SlashCommandInteractionEvent) {
        val config = SO.getConfig()
        if (!checkRole(event, config.settings.roles.admin)) {
            event.reply("You don't have the permission to do that.").setEphemeral(true).complete()
            return
        }
        val opt = event.getOption("user")
        if (opt == null) {
            event.reply("You must specify a user.").setEphemeral(true).complete()
            return
        }
        val guild = event.guild!!
        val verified = guild.getRoleById(config.settings.roles.verified)
        if (verified == null) {
            event.reply("The verified role is not set.").setEphemeral(true).complete()
            return
        }
        val member = opt.asMember!!
        if (member.roles.contains(verified)) {
            event.reply("This user is already verified.").setEphemeral(true).complete()
            return
        }
        member.guild.addRoleToMember(member, verified).queue()

        val channel = guild.getTextChannelById(config.settings.verify.welcomeChannel)
        if (channel == null) {
            event.reply("The welcome channel is not set.").setEphemeral(true).complete()
            return
        }

        val year = Calendar.getInstance().get(Calendar.YEAR)
        val eb = EmbedBuilder()
            .setAuthor("Le Monde de la Nuit")
            .setDescription("Bienvenue à ${member.asMention} sur le serveur !")
            .setColor(0x00FF00)
            .setTimestamp(Date().toInstant())
        if (year != 2023) {
            eb.setFooter("Le Monde de la Nuit - © $year - 2023")
        } else {
            eb.setFooter("Le Monde de la Nuit - © 2023")
        }

        channel.sendMessageEmbeds(eb.build()).queue()

        event.reply("The user ${member.asMention} is now verified.").setEphemeral(true).complete()
    }
}