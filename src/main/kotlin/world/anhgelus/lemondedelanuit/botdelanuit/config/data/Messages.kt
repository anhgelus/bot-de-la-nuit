package world.anhgelus.lemondedelanuit.botdelanuit.config.data

data class Messages(
    val error: Error,
    val command: Command
) {
    data class Error(val common: ErrorCommon, val argument: ErrorArgument)
    data class ErrorCommon(val badPermissions: String, val notSet: ErrorCommonNotSet)
    data class ErrorCommonNotSet(val verified: String, val admin: String, val mod: String)
    data class ErrorArgument(val notSpecified: ErrorArgumentNotSpecified)
    data class ErrorArgumentNotSpecified(val user: String)

    data class Command(val verify: CommandVerify)
    data class CommandVerify(val description: String, val reply: String, val embedBroadcast: String,
                             val error: CommandVerifyError, val content: CommandVerifyContent)
    data class CommandVerifyError(val welcomeChannelNotSet: String)
    data class CommandVerifyContent(val userOption: String)
}
