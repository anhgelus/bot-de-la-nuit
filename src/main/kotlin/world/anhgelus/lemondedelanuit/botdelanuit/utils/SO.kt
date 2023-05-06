package world.anhgelus.lemondedelanuit.botdelanuit.utils

import world.anhgelus.lemondedelanuit.botdelanuit.config.data.Config
import world.anhgelus.lemondedelanuit.botdelanuit.config.data.Messages

object SO {
    private lateinit var config: Config
    private lateinit var messages: Messages

    fun setConfig(config: Config) {
        this.config = config
    }

    fun getConfig(): Config {
        return config
    }

    fun setMessages(messages: Messages) {
        this.messages = messages
    }

    fun getMessages(): Messages {
        return messages
    }
}