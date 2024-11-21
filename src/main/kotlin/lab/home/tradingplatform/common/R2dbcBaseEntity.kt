package lab.home.tradingplatform.common

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

    override fun isNew(): Boolean = createdAt == null && updatedAt == null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as R2dbcBaseEntity

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String {
        return "R2dbcBaseEntity(id=$id, isLocked=$isLocked, isEnabled=$isEnabled, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
