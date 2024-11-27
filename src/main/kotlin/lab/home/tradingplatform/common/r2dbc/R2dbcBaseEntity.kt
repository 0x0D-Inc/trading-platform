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
    /* TODO
        1. 애초에 save() 전에 getById() -> 있는지 DB에 한 번확인 -> 별로 안좋은 방법
        2. isNew 를 고쳐서 ID 기반으로 isNew() { id == null } return true -> 우리가 ID를 생성해서 넣어 주지 않는 이상은 동작
                - 우리는 ID를 생성 해서 넣어 주기 때문에
                - 실수할 확률 -> UUID 를 생성해서 넣어 주는데, 새로운 엔티티인데, UUID 를 실수로 넣어 줘서
        3. upsert 쿼리??
            postgresql
                insert 시도했다가, 안되면 update 할 수 있는 쿼리가 있음 -> DB 모두 지원하지 않을 것임.. 호환성 문제.
        4. 서버가 죽으면 -> (insert -> update)
        save 호출 할 때, isNew 로 판단 하는데, insert 냐 ? update 냐?
        insert 로 수행 될 것임. 레코드가 존재하면 -> 에러가 -> Exception e
        update 다시 호출 ???
        5. 중간에 redis 같은 캐시 DB를 쓴다
     */
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
