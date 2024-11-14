package lab.home.tradingplatform.auth.adapter.`in`.web

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/"])
class HomeController {
    @GetMapping
    suspend fun home(): String = "Welcome to trading platform"

    @GetMapping("/coroutines/stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun coroutineFlow() : Flow<Int> = flow {
        (1..100).asFlow()
            .collect {
                delay(100)
            }
    }

    /*@GetMapping("/{id}")
    suspend fun getUser(@PathVariable id: Int): ServerResponse {
        val user = users.find { it.id == id }
            ?: return ServerResponse.notFound().buildAndAwait()

        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(user)
    }

    @GetMapping
    suspend fun getAllUsers(): ServerResponse {
        val usersFlow: Flow<User> = flow {
            users.forEach {
                emit(it)
            }
        }
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(usersFlow)
    }

    @PostMapping
    suspend fun createUser(@RequestBody user: User): ServerResponse {
        users.add(user)
        return ServerResponse.created(null)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(user)
    }*/
}
