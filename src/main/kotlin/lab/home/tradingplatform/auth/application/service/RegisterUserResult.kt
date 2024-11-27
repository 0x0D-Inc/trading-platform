package lab.home.tradingplatform.auth.application.service

import lab.home.tradingplatform.auth.domain.TwoFactorAuth
import lab.home.tradingplatform.auth.domain.User
import lab.home.tradingplatform.auth.domain.UserRole
import java.util.UUID

data class RegisterUserResult(
    val id: UUID,
    val fullName: String,
    val email: String,
    val twoFactorAuth: TwoFactorAuth,
    val role: UserRole
)

// Domain to Controller
fun User.toRegisterUserResult() : RegisterUserResult = RegisterUserResult(
    this.id!!.value,        // XXX
    this.fullName,
    this.email,
    this.twoFactorAuth,
    this.userRole
)
