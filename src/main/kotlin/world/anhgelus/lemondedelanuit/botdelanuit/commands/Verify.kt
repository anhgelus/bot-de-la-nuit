package world.anhgelus.lemondedelanuit.botdelanuit.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import world.anhgelus.lemondedelanuit.botdelanuit.utils.Context
import world.anhgelus.lemondedelanuit.botdelanuit.utils.MessageParser
import world.anhgelus.lemondedelanuit.botdelanuit.utils.SO
import java.util.*

object Verify : BaseCommand() {
    override val name = "verify"
    override fun generateCommand(): CommandData {
        val messages = SO.getMessages()
        return Commands.slash(name, messages.command.verify.description)
            .addOption(OptionType.USER, "user", messages.command.verify.content.userOption, true)
    }

    override fun handle(event: SlashCommandInteractionEvent) {
        val config = SO.getConfig()
        val messages = SO.getMessages()
        if (!checkRole(event, config.settings.roles.admin)) {
            event.reply(messages.error.common.badPermissions).setEphemeral(true).complete()
            return
        }
        val opt = event.getOption("user")
        if (opt == null) {
            event.reply(messages.error.argument.notSpecified.user).setEphemeral(true).complete()
            return
        }
        val guild = event.guild!!
        val verified = guild.getRoleById(config.settings.roles.verified)
        if (verified == null) {
            event.reply(messages.error.common.notSet.verified).setEphemeral(true).complete()
            return
        }
        val member = opt.asMember!!
        if (member.roles.contains(verified)) {
            event.reply(messages.command.verify.error.userAlreadyVerified).setEphemeral(true).complete()
            return
        }
        member.guild.addRoleToMember(member, verified).queue()

        val channel = guild.getTextChannelById(config.settings.verify.welcomeChannel)
        if (channel == null) {
            event.reply(messages.command.verify.error.welcomeChannelNotSet).setEphemeral(true).complete()
            return
        }

        val context = Context(member, event.guild!!, event.channel)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val eb = EmbedBuilder()
            .setAuthor("Le Monde de la Nuit")
            .setDescription(MessageParser.parseMessage(messages.command.verify.embedBroadcast, context))
            .setColor(0x00FF00)
            .setTimestamp(Date().toInstant())
        if (year != 2023) {
            eb.setFooter("Le Monde de la Nuit - © $year - 2023")
        } else {
            eb.setFooter("Le Monde de la Nuit - © 2023")
        }

        channel.sendMessageEmbeds(eb.build()).queue()

        event.reply(MessageParser.parseMessage(messages.command.verify.reply, context)).setEphemeral(true).complete()
    }
}