package world.anhgelus.lemondedelanuit.botdelanuit.config

import cc.ekblad.toml.decode
import cc.ekblad.toml.tomlMapper
import net.dv8tion.jda.api.entities.Activity
import world.anhgelus.lemondedelanuit.botdelanuit.config.data.Config
import java.nio.file.Path

object Manager {
    const val configFolderPath = "config/"
    const val configName = "config.toml"

    fun getConfig(): Config {
        val mapper = tomlMapper { }
        val tomlFile = Path.of(configFolderPath+ configName)
        return mapper.decode<Config>(tomlFile)
    }

    fun generateActivity(config: Config): Activity {
        val status = config.settings.status
        return when(status.type) {
            "watching" -> Activity.watching(status.content)
            "playing" -> Activity.playing(status.content)
            "listening" -> Activity.listening(status.content)
            "competing" -> Activity.competing(status.content)
            else -> throw IllegalArgumentException("Unknown status type")
        }
    }
}