package lab.home.tradingplatform.auth.adapter.out.persistence

import lab.home.tradingplatform.auth.domain.UserRole
import lab.home.tradingplatform.auth.domain.VerificationType
import lab.home.tradingplatform.common.R2dbcBaseEntity
import lab.home.tradingplatform.common.UUIDv7
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

// reference: https://veluxer62.github.io/explanation/about-entity-and-value-object/#entity
// Entity : 식별성, 연속성을 가져야 함
// 연속성 : 내용이 변경 되어도 동일한 객체임을 추적 할 수 있는 성질
// Entity, VO 구분 기준
// @Table(name = "users")
// class UserR2dbcEntity(
//    @Column(value = "full_name")
//    val fullName: String,
//    @Column("email")
//    val email: String,
//    @Column("user_role")
//    val userRole: UserRole,
//    @Column("verification_type")
//    val verificationType: VerificationType,
//    @CreatedDate
//    @Column("created_at")
//    var createdAt: Instant? = null,
//    @LastModifiedDate
//    @Column("updated_at")
//    var updatedAt: Instant? = null,
//    @Id
//    @Column("id")
//    private val id: UUID = UUIDv7.randomUUID()
// ) : Persistable<UUID> {
//
//    override fun getId(): UUID = id
//
//    override fun isNew(): Boolean = createdAt == null && updatedAt == null
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as UserR2dbcEntity
//
//        return id == other.id
//    }
//
//    override fun hashCode(): Int = id.hashCode()
//
//    override fun toString(): String =
//        "UserR2dbcEntity(fullName='$fullName', email='$email', userRole=$userRole, verificationType=$verificationType, createdAt=$createdAt, updatedAt=$updatedAt, id=$id)"
// }

@Table(name = "users")
class UserR2dbcEntity(
    @Column(value = "full_name")
    val fullName: String,
    @Column("email")
    val email: String,
    @Column("user_role")
    val userRole: UserRole,
    @Column("verification_type")
    val verificationType: VerificationType,
    id: UUID = UUIDv7.randomUUID()
) : R2dbcBaseEntity(id) {
    override fun toString(): String =
        "UserR2dbcEntity(fullName='$fullName', email='$email', userRole=$userRole, verificationType=$verificationType, createdAt=$createdAt, updatedAt=$updatedAt, id=$id)"
}
