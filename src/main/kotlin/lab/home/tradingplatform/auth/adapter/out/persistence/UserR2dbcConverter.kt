package lab.home.tradingplatform.auth.adapter.out.persistence

import lab.home.tradingplatform.auth.domain.UserRole
import lab.home.tradingplatform.auth.domain.VerificationType
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.r2dbc.convert.EnumWriteSupport

class UserR2dbcConverter

@WritingConverter
class UserRoleWriteConverter : EnumWriteSupport<UserRole>()

@ReadingConverter
class UserRoleReadConverter : Converter<String, UserRole> {
    override fun convert(source: String): UserRole = UserRole.valueOf(source)
}

@WritingConverter
class VerificationTypeWriteConverter : EnumWriteSupport<VerificationType>()

@ReadingConverter
class VerificationTypeReadConverter : Converter<String, VerificationType> {
    override fun convert(source: String): VerificationType = VerificationType.valueOf(source)
}
