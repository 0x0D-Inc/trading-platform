package lab.home.tradingplatform.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import io.kotest.core.spec.style.FreeSpec

internal class UseCaseKonsistTest :
    FreeSpec({
        "Every use case reside in input port package" {
            Konsist
                .scopeFromProject()
                .classes()
                .withNameEndingWith("UseCase")
                .assertTrue(
                    testName = this.testCase.name.testName
                ) {
                    it.resideInPackage("..port.in..")
                }
        }
    })
