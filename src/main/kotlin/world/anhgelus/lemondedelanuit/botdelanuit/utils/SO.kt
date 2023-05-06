package world.anhgelus.lemondedelanuit.botdelanuit.utils

import world.anhgelus.lemondedelanuit.botdelanuit.config.data.Config

object SO {
    private lateinit var config: Config

    fun setConfig(config: Config) {
        this.config = config
    }

    fun getConfig(): Config {
        return config
    }
}