package lab.home.tradingplatform.auth.adapter.out.persistence

import lab.home.tradingplatform.auth.domain.TwoFactorAuth
import lab.home.tradingplatform.auth.domain.User
import lab.home.tradingplatform.auth.domain.UserId
import lab.home.tradingplatform.auth.domain.UserRole
import lab.home.tradingplatform.auth.domain.VerificationType
import lab.home.tradingplatform.common.r2dbc.R2dbcBaseEntity
import lab.home.tradingplatform.common.UUIDv7
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

// Entity
@Table(name = "users")
class UserR2dbcEntity(
    @Column(value = "full_name")
    val fullName: String,
    @Column("email")
    val email: String,
    @Column("user_role")
    val userRole: UserRole, // TODO: to separate table
    @Column("two_factor_auth_is_enabled")
    val twoFactorAuthIsEnabled: Boolean,
    @Column("two_factor_auth_send_to")
    val twoFactorAuthSendTo: VerificationType,
    id: UUID = UUIDv7.randomUUID()
) : R2dbcBaseEntity(id) {
    // Additional features
    override fun toString(): String =
        "UserR2dbcEntity(fullName='$fullName', email='$email', userRole=$userRole, towFactorAuthIsEnabled=$twoFactorAuthIsEnabled, toFactorAuthSendTo=$twoFactorAuthSendTo, entityInfo=${super.toString()})"
}

/*fullName: String,       // Should we merge to a class??
email: String,
userRole: UserRole,
verificationType: VerificationType,
isLocked: Boolean,
isEnabled: Boolean,
createdAt: Instant? = null,
updatedAt: Instant? = null,
id: UserId?*/

fun User.toR2dbcEntity(): UserR2dbcEntity =
    UserR2dbcEntity(
        this.fullName,
        this.email,
        this.userRole,
        this.twoFactorAuth.isEnabled,
        this.twoFactorAuth.sendTo,
        this.id!!.value
    )

fun UserR2dbcEntity.toUserEntity(): User =
    User(
        this.fullName,
        this.email,
        this.userRole,
        TwoFactorAuth(
            this.twoFactorAuthSendTo,
            this.twoFactorAuthIsEnabled
        ),
        this.isLocked,
        this.isEnabled,
        this.createdAt,
        this.updatedAt,
        UserId(this.id)
    )
