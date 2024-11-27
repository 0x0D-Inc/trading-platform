package lab.home.tradingplatform.common.exception

// NOTE: 이게 필요하나?
// NOTE: (HTTP status code, Internal Code, Message)
enum class CommonErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    // 서버 관련 에러
    INTERNAL_SERVER_ERROR(500, "C000", "내부 서버에 오류가 발생했습니다."),

    // 입력 검증 관련 에러
    INVALID_INPUT(400, "C001", "잘못된 입력입니다."),
    MISSING_REQUIRED_FIELD(400, "C002", "필수 필드가 누락되었습니다."),
    INVALID_FORMAT(400, "C003", "입력 형식이 올바르지 않습니다."),

    // 인증 및 권한 관련 에러
    UNAUTHORIZED_ACCESS(401, "C100", "인증되지 않은 접근입니다."),
    TOKEN_EXPIRED(401, "C101", "인증 토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "C102", "유효하지 않은 토큰입니다."),
    INSUFFICIENT_PERMISSIONS(403, "C103", "해당 작업을 수행할 권한이 없습니다."),

    // 리소스 관련 에러
    RESOURCE_NOT_FOUND(404, "C200", "요청한 리소스를 찾을 수 없습니다."),
    RESOURCE_ALREADY_EXISTS(409, "C201", "이미 존재하는 리소스입니다."),
    RESOURCE_CONFLICT(409, "C202", "리소스 충돌이 발생했습니다."),

    // 비즈니스 로직 관련 에러
    INSUFFICIENT_FUNDS(400, "C300", "잔액이 부족합니다."),
    EXCEEDED_LIMIT(400, "C301", "한도를 초과했습니다."),
    INVALID_OPERATION(400, "C302", "유효하지 않은 작업입니다."),

    // 외부 서비스 연동 관련 에러
    EXTERNAL_SERVICE_ERROR(500, "C400", "외부 서비스 연동 중 오류가 발생했습니다."),
    EXTERNAL_SERVICE_TIMEOUT(504, "C401", "외부 서비스 응답 시간이 초과되었습니다."),

    // 데이터베이스 관련 에러
    DATABASE_ERROR(500, "C500", "데이터베이스 작업 중 오류가 발생했습니다."),
    TRANSACTION_FAILED(500, "C501", "트랜잭션 처리에 실패했습니다."),

    // 파일 처리 관련 에러
    FILE_UPLOAD_ERROR(500, "C600", "파일 업로드 중 오류가 발생했습니다."),
    FILE_NOT_FOUND(404, "C601", "요청한 파일을 찾을 수 없습니다."),
    INVALID_FILE_FORMAT(400, "C602", "지원하지 않는 파일 형식입니다.")
}
