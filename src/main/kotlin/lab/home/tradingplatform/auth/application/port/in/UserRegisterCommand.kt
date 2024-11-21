package lab.home.tradingplatform.auth.application.port.`in`

data class UserRegisterCommand(
    val fullName: String,
    val email: String,
    val password: String,
    val mobile: String
) {
    init {
        /* Verify arguments */
    }
}
