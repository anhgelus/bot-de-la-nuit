package world.anhgelus.lemondedelanuit.botdelanuit

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import world.anhgelus.lemondedelanuit.botdelanuit.commands.BaseCommand
import world.anhgelus.lemondedelanuit.botdelanuit.commands.Verify
import world.anhgelus.lemondedelanuit.botdelanuit.config.Manager
import world.anhgelus.lemondedelanuit.botdelanuit.config.data.Config
import world.anhgelus.lemondedelanuit.botdelanuit.event.SlashCommands
import world.anhgelus.lemondedelanuit.botdelanuit.utils.Commands
import world.anhgelus.lemondedelanuit.botdelanuit.utils.SO
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val config = Manager.getConfig()

    val commands = HashMap<String, BaseCommand>()
    commands[Verify.name] = Verify

    val discord = JDABuilder.createDefault(args[0])
        .enableIntents(EnumSet.allOf(GatewayIntent::class.java))
        .setActivity(Manager.generateActivity(config))
        .addEventListeners(SlashCommands(commands))
        .build()
        .awaitReady()

    SO.setConfig(config)

    setupCommands(discord, config, commands)

    println("Bot started and connected!")
}

private fun setupCommands(discord: JDA, config: Config, commands: Map<String, BaseCommand>) {
    val guild = discord.getGuildById(config.settings.guild)!!

    Commands.createCommands(guild, commands)
}
