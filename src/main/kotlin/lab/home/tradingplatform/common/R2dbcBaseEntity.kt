package lab.home.tradingplatform.common

import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import java.time.Instant

abstract class BaseEntityR2DBC {
    var createdAt: Instant = Instant.now()
    var updatedAt: Instant = Instant.now()
}

@WritingConverter
class BaseEntityWriteConverter : Converter<BaseEntityR2DBC, Map<String, Any>> {
    override fun convert(source: BaseEntityR2DBC): Map<String, Any> {
        val result = mutableMapOf<String, Any>()
        result["created_at"] = source.createdAt
        result["updated_at"] = source.updatedAt
        return result
    }
}

@ReadingConverter
class BaseEntityReadConverter : Converter<Row, BaseEntityR2DBC> {
    override fun convert(source: Row): BaseEntityR2DBC {
        return object : BaseEntityR2DBC() {
            init {
                // TODO : Throw if can't get times
                createdAt = source.get("created_at", Instant::class.java) ?: Instant.now()
                updatedAt = source.get("updated_at", Instant::class.java) ?: Instant.now()
            }
        }
    }
}
