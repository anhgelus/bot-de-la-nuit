package world.anhgelus.lemondedelanuit.botdelanuit.config

import cc.ekblad.toml.decode
import cc.ekblad.toml.tomlMapper
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
}