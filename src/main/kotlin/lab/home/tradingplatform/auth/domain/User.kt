package lab.home.tradingplatform.auth.domain

import java.util.UUID
import java.time.Instant

@JvmInline
value class UserId(val value: UUID)

class User(
    fullName: String,       // Should we merge to a class??
    email: String,
    userRole: UserRole,
    twoFactorAuth: TwoFactorAuth,
    isLocked: Boolean,
    isEnabled: Boolean,
    createdAt: Instant? = null,
    updatedAt: Instant? = null,
    id: UserId?
) {
    var id: UserId? = id
        private set
    var fullName: String = fullName
        private set
    var email: String = email
        private set
    var userRole: UserRole = userRole
        private set
    var twoFactorAuth: TwoFactorAuth = twoFactorAuth
        private set
    var isLocked: Boolean = isLocked
        private set
    var isEnabled: Boolean = isEnabled
        private set
    var createdAt: Instant? = createdAt
        private set
    var updatedAt: Instant? = updatedAt
        private set

    init {
        /* Domain model verification */
    }

    companion object {
        /* factory functions */
    }

    fun changeTwoFactorAuth(twoFactorAuth: TwoFactorAuth): User {
        this.twoFactorAuth = twoFactorAuth
        return this
    }

    fun changeUserRole (newRole: UserRole): User {
        /* Invalid User Role Exception ?? */
        this.userRole = newRole
        return this
    }
}
