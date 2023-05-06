package world.anhgelus.lemondedelanuit.botdelanuit.utils

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.channel.Channel

object MessageParser {
    /**
     * Parse a message with the context
     * @param content the message to parse
     * @param context the context
     * @return the parsed message
     */
    fun parseMessage(content: String, context: Context): String {
        var new = content
        new = replacer(new, "{user.name}", context.user!!.nickname!!)
        new = replacer(new, "{user.mention}", context.user.asMention)
        new = replacer(new, "{guild.name}", context.guild!!.name)
        return new
    }

    /**
     * Parse generally the message
     * @param content the message to parse
     * @param toReplace the string to replace
     * @param with the string to replace with
     * @return the parsed message
     */
    private fun replacer(content: String, toReplace: String, with: String): String {
        if (content.contains(toReplace)) {
            return content.replace(toReplace, with)
        }
        return content
    }
}

data class Context(val user: Member?, val guild: Guild?, val channel: Channel?)
