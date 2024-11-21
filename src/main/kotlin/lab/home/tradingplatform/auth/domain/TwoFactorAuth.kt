package lab.home.tradingplatform.auth.domain

data class TwoFactorAuth(
    val sendTo: VerificationType = VerificationType.NONE,
    val isEnabled: Boolean = false
)
