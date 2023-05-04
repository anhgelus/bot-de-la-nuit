package world.anhgelus.lemondedelanuit.botdelanuit.config.data

data class Config(
    val db: DB,
) {
    data class DB(val dbType: String, val host: String, val port: Short, val user: String, val password: String,
                  val dbName: String)
}
