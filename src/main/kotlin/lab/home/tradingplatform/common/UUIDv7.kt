package lab.home.tradingplatform.common

import java.nio.ByteBuffer
import java.security.SecureRandom
import java.util.UUID

object UUIDv7 {
    private val random = SecureRandom()

    fun randomUUID(): UUID {
        val value = randomBytes()
        val buf = ByteBuffer.wrap(value)
        val high = buf.long
        val low = buf.long
        return UUID(high, low)
    }

    private fun randomBytes(): ByteArray {
        // Random bytes
        val value = ByteArray(16)
        random.nextBytes(value)

        // Current timestamp in ms
        val timestamp = ByteBuffer.allocate(Long.SIZE_BYTES)
        timestamp.putLong(System.currentTimeMillis())

        // Timestamp
        System.arraycopy(timestamp.array(), 2, value, 0, 6)

        // Version and variant
        value[6] = (value[6].toInt() and 0x0F or 0x70).toByte()
        value[8] = (value[8].toInt() and 0x3F or 0x80).toByte()

        return value
    }
}
