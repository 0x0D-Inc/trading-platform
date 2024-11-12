package lab.home.tradingplatform.common

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import java.nio.ByteBuffer
import java.util.*

class R2dbcConverter

@WritingConverter
class UUIDToByteArrayConverter : Converter<UUID, ByteArray> {
    override fun convert(source: UUID): ByteArray {
        val byteBuffer = ByteBuffer.wrap(ByteArray(16))
        byteBuffer.putLong(source.mostSignificantBits)
        byteBuffer.putLong(source.leastSignificantBits)
        return byteBuffer.array()
    }
}

@ReadingConverter
class ByteArrayToUUIDConverter : Converter<ByteArray, UUID> {
    override fun convert(source: ByteArray): UUID {
        val byteBuffer = ByteBuffer.wrap(source)
        val high = byteBuffer.long
        val low = byteBuffer.long
        return UUID(high, low)
    }
}
