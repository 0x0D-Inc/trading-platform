package lab.home.tradingplatform.styler

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StylerRepository : CoroutineCrudRepository<Styler, Int> {
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
    ): Unit
}
