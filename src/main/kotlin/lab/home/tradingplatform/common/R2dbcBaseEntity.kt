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
}

// @WritingConverter
// class BaseEntityWriteConverter : Converter<BaseEntityR2DBC, Map<String, Any>> {
//    override fun convert(source: BaseEntityR2DBC): Map<String, Any> {
//        val result = mutableMapOf<String, Any>()
//        result["created_at"] = source.createdAt
//        result["updated_at"] = source.updatedAt
//        return result
//    }
// }
//
// @ReadingConverter
// class BaseEntityReadConverter : Converter<Row, BaseEntityR2DBC> {
//    override fun convert(source: Row): BaseEntityR2DBC =
//        object : BaseEntityR2DBC() {
//            init {
//                // TODO : Throw if can't get times
//                createdAt = source.get("created_at", Instant::class.java) ?: Instant.now()
//                updatedAt = source.get("updated_at", Instant::class.java) ?: Instant.now()
//            }
//        }
// }
