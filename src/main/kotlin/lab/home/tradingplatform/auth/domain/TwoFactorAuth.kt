package lab.home.tradingplatform.auth.domain

data class TwoFactorAuth(
    val isEnabled: Boolean = false,
    val sendTo: VerificationType
)
