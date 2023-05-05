package world.anhgelus.lemondedelanuit.botdelanuit.config.data

/**
 * @property db the database config
 * @property settings the settings of the bot
 */
data class Config(
    val db: DB,
    val settings: Settings,
) {
    /**
     * @property dbType the type of the db (pgsql, mysql...)
     * @property host the host
     * @property port the port of the db
     * @property user the user to connect to the db
     * @property password the password of the user
     * @property dbName the name of the db
     */
    data class DB(val dbType: String, val host: String, val port: Int, val user: String, val password: String,
                  val dbName: String)

    /**
     * @property type the type of the status
     * @property content the content of the status
     */
    data class Status(val type: String, val content: String)

    /**
     * @property status the settings of the status
     */
    data class Settings(val status: Status)
}
