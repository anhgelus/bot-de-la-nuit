import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import java.util.*

fun main(args: Array<String>) {
    val discord = JDABuilder.createDefault(args[0])
        .enableIntents(EnumSet.allOf(GatewayIntent::class.java))
        .build()
}