package api.kotlin.project.users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching //Permite utilização de cache, para diminuir requisições no banco de dados
class UsersApplication

fun main(args: Array<String>) {
	runApplication<UsersApplication>(*args)
}
