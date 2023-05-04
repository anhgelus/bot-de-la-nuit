package world.anhgelus.lemondedelanuit.botdelanuit.config.data

data class Config(
    val db: DB,
    val settings: Settings,
) {
    data class DB(val dbType: String, val host: String, val port: Short, val user: String, val password: String,
                  val dbName: String)
    data class Status(val type: String, val content: String)
    data class Settings(val status: Status)
}
