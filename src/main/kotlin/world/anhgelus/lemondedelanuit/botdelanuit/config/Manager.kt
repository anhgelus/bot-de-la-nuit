package world.anhgelus.lemondedelanuit.botdelanuit.config

import cc.ekblad.toml.TomlMapper
import cc.ekblad.toml.configuration.TomlMapperConfigurator
import cc.ekblad.toml.decode
import cc.ekblad.toml.tomlMapper
import net.dv8tion.jda.api.entities.Activity
import world.anhgelus.lemondedelanuit.botdelanuit.config.data.Config
import world.anhgelus.lemondedelanuit.botdelanuit.config.data.Messages
import world.anhgelus.lemondedelanuit.botdelanuit.utils.file.Resources
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.nio.file.NoSuchFileException
import java.nio.file.Path

object Manager {
    const val configFolderPath = "config/"
    const val configName = "config.toml"
    const val messagesName = "messages.toml"

    /**
     * Get the config from the files
     */
    fun getConfig(): Config {
        val mapper = tomlMapper {
            mapping<Config.Verify>("welcome_channel" to "welcomeChannel")
        }
        return getConfig<Config>(configName, mapper)
    }

    private inline fun <reified T> getConfig(name: String, mapper: TomlMapper): T {
        val path = "$configFolderPath$name"
        val tomlFile = Path.of(path)
        return try {
            mapper.decode<T>(tomlFile)
        } catch (e: NoSuchFileException) {
            println("File not found, creating it")
            val file = File(path)
            val dir = File(configFolderPath)
            if (!dir.exists()) {
                assert(dir.mkdir())
            }
            if (!file.createNewFile()) {
                throw IllegalStateException("Impossible to create the file with the path $path")
            }
            val writer = FileWriter(path)
            writer.write(Resources.getResourceFileAsString("/examples/$path")!!)
            writer.close()
            mapper.decode<T>(file.toPath())
        }
    }

    /**
     * Generate the activity from the config
     *
     * @property config the config file
     */
    fun generateActivity(config: Config): Activity {
        val status = config.settings.status
        if (status.type == "") {
            throw IllegalArgumentException("Status type not set")
        }
        if (status.content == "") {
            throw IllegalArgumentException("Status content not set")
        }
        return when(status.type) {
            "watching" -> Activity.watching(status.content)
            "playing" -> Activity.playing(status.content)
            "listening" -> Activity.listening(status.content)
            "competing" -> Activity.competing(status.content)
            else -> throw IllegalArgumentException("Unknown status type")
        }
    }
}