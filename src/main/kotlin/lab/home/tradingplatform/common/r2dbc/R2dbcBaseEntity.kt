package lab.home.tradingplatform.common.r2dbc

import lab.home.tradingplatform.common.UUIDv7
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import java.time.Instant
import java.util.UUID

abstract class R2dbcBaseEntity(
    @Id
    @Column("id")
    private val id: UUID = UUIDv7.randomUUID()
) : Persistable<UUID> {
    @Column("is_locked")
    var isLocked: Boolean = false

    @Column("is_enabled")
    var isEnabled: Boolean = true

    @CreatedDate
    @Column("created_at")
    var createdAt: Instant? = null

    @LastModifiedDate
    @Column("updated_at")
    var updatedAt: Instant? = null

    override fun getId(): UUID = id

    // XXX: 만약, 서버가 죽어서 메모리에 있는 내용이 모두 날아가버린다면?? -> isNew 를 어떻게 판단 할까?
    override fun isNew(): Boolean = createdAt == null && updatedAt == null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as R2dbcBaseEntity

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String =
        "R2dbcBaseEntity(id=$id, isLocked=$isLocked, isEnabled=$isEnabled, createdAt=$createdAt, updatedAt=$updatedAt)"
}
