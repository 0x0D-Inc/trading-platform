package lab.home.tradingplatform.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import io.kotest.core.spec.style.FreeSpec

internal class HexagonalArchitectureTest :
    FreeSpec({
        val rootPackage = "lab.home.tradingplatform"

        "Hexagonal architecture layers have correct dependencies" {
            Konsist
                .scopeFromProject()
                .assertArchitecture {
                    val domain = Layer("Domain", "$rootPackage..domain..")
                    val appService = Layer("Application Service", "$rootPackage..application.service..")
                    val portIn = Layer("Application Port In", "$rootPackage..application.port.in..")
                    val portIOut = Layer("Application Port Out", "$rootPackage..application.port.out..")
                    val adapterIn = Layer("Adapter In", "$rootPackage..adapter.in..")
                    val adapterOut = Layer("Adapter Out", "$rootPackage..adapter.out..")

                    // define architecture layer dependencies
//                domain.dependsOnNothing()
                }
        }
    })
