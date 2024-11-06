package lab.home.tradingplatform

import io.kotest.common.runBlocking
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import lab.home.tradingplatform.styler.Styler
import lab.home.tradingplatform.styler.StylerRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(MySQLContainerConfiguration::class)
internal class TradingPlatformApplicationTest(
    private val sut: StylerRepository,
) : FunSpec({
        lateinit var styler: Styler

        beforeTest {
            styler = Styler(null, "테스트", "1")
            styler =
                runBlocking {
                    sut.save(styler)
                }
        }

        test("update with modifying") {
            runBlocking {
                val result = sut.updateWithModifying("P", styler.id!!)
                result shouldNotBe null
            }
        }
    })
