package lab.home.tradingplatform.auth.domain

import lab.home.tradingplatform.common.UUIDv7
import java.util.UUID

class UserId {
    val id: UUID = UUIDv7.randomUUID()
}
