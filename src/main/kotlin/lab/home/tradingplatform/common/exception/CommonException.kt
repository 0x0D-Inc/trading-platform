package lab.home.tradingplatform.common.exception

// NOTE: 이게 필요하나?
class CommonException(
    val errorCode: CommonErrorCode,
    override val message: String? = errorCode.message
) : RuntimeException()
