package lab.home.tradingplatform.auth.adapter.web.`in`

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/"])
class HomeController {
    @GetMapping
    suspend fun home(): String = "Welcome to trading platform"
}
