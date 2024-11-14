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

    @Query("UPDATE users SET full_name = :fullName WHERE id = :id")
    suspend fun updateWithoutModifying(
        fullName: String,
        id: UUID
    ): Unit
}

/*
suspend fun findByShopId(shopId: Int): Styler?

@Query("SELECT * FROM styler WHERE name = :name")
suspend fun findByName(name: String): Flow<Styler>

@Modifying
@Query("UPDATE styler SET name = :name WHERE id = :id")
suspend fun updateWithModifying(
    name: String,
    id: Int
): Int?

@Query("UPDATE styler SET name = :name WHERE id = :id")
suspend fun updateWithoutModifying(
    name: String,
    id: Int
): Unit*/
