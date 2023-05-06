package world.anhgelus.lemondedelanuit.botdelanuit.config.data

data class Messages(
    val error: Error,
    val command: Command
) {
    /**
     * @property common the common errors
     * @property argument the argument errors
     */
    data class Error(val common: ErrorCommon, val argument: ErrorArgument)

    /**
     * @property badPermissions the bad permissions error
     * @property notSet the was not set errors
     */
    data class ErrorCommon(val badPermissions: String, val notSet: ErrorCommonNotSet)

    /**
     * @property verified the verified id was not set error
     * @property admin the admin id was not set error
     * @property mod the mod id was not set error
     */
    data class ErrorCommonNotSet(val verified: String, val admin: String, val mod: String)

    /**
     * @property notSpecified the was not specified errors
     */
    data class ErrorArgument(val notSpecified: ErrorArgumentNotSpecified)

    /**
     * @property user the user was not specified error
     */
    data class ErrorArgumentNotSpecified(val user: String)

    /**
     * @property verify the verify command
     */
    data class Command(val verify: CommandVerify)

    /**
     * @property description the verify command's description
     * @property reply the verify command's reply
     * @property embedBroadcast the verify command's embed broadcast content
     * @property error the verify command's specific errors
     * @property content the verify command options' content
     */
    data class CommandVerify(val description: String, val reply: String, val embedBroadcast: String,
                             val error: CommandVerifyError, val content: CommandVerifyContent, val dm: String)

    /**
     * @property welcomeChannelNotSet the welcome channel was not set error
     * @property userAlreadyVerified the user is already verified error
     */
    data class CommandVerifyError(val welcomeChannelNotSet: String, val userAlreadyVerified: String)

    /**
     * @property userOption the user option description content
     */
    data class CommandVerifyContent(val userOption: String)
}
