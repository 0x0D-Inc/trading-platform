package lab.home.tradingplatform.styler

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("styler")
data class Styler(
    @Id
    val id: Int?,
    var name: String,
    var shopId: String,
)
