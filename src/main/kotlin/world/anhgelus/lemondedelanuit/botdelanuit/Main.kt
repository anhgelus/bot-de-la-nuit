package world.anhgelus.lemondedelanuit.botdelanuit

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import world.anhgelus.lemondedelanuit.botdelanuit.config.Manager
import java.util.*

fun main(args: Array<String>) {
    val config = Manager.getConfig()

    val discord = JDABuilder.createDefault(args[0])
        .enableIntents(EnumSet.allOf(GatewayIntent::class.java))
        .setActivity(Manager.generateActivity(config))
        .build()

    println("Bot connected!")
}