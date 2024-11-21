package lab.home.tradingplatform.auth.adapter.out.persistence

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserCoroutineRepository : CoroutineCrudRepository<UserR2dbcEntity, UUID> {
    override suspend fun findById(id: UUID): UserR2dbcEntity?

    @Query("SELECT * FROM users WHERE full_name = :fullName")
    suspend fun findByFullName(fullName: String): Flow<UserR2dbcEntity>

    @Modifying // Not Needed
    @Query("UPDATE users SET full_name = :fullName WHERE id = :id")
    suspend fun updateWithModifying(
        fullName: String,
        id: UUID
    ): Int?
}
