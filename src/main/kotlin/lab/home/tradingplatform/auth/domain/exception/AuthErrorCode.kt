package lab.home.tradingplatform.auth.domain.exception

enum class AuthErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    INVALID_CREDENTIALS(401, "A001", "잘못된 인증 정보입니다"),
    USER_NOT_FOUND(404, "A002", "사용자를 찾을 수 없습니다"),
    USER_ALREADY_EXIST(409, "A003", "이미 존재하는 사용자입니다"),
    USER_EMAIL_CONFLICT(409, "A004", "이미 존재하는 Email 입니다")
}
