package api.kotlin.project.users.service

import api.kotlin.project.users.dto.UserDto
import api.kotlin.project.users.model.User
import org.springframework.stereotype.Service
import java.util.*

//Anotação para classes representantes de serviços
@Service

class UserService(private var users: List<User> = ArrayList()) {

//region init-old
//    init {
//        val user1 = User(
//            id = 1,
//            name = "Jhoy",
//            email = "jhoymartins@gmail.com",
//            password = "123456"
//        )
//        val user2 = User(
//            id = 2,
//            name = "João",
//            email = "joaozinho1@gmail.com",
//            password = "123456"
//        )
//        val user3 = User(
//            id = 3,
//            name = "Pedro",
//            email = "pedrosantoss@gmail.com",
//            password = "123456"
//        )
//        users = Arrays.asList(user1, user2, user3)
//    }
//endregion

    fun allUsers(): List<User> {
        return users
    }

    fun searchForId(id: Long): User {
        return users.stream().filter { u ->
            u.id == id
        }.findFirst().get()
    }
//region added per day
//    fun addedPerDay(date: LocalDate?): User {
//        //SE DATA == VAZIO -> MEDIA DE TODOS OS USUARIOS CADASTRADOS POR DIA, DESDE O INÍCIO
//
//
//        //SE DATA != VAZIO -> QUANTIDADE DE USUÁRIOS CADASTRADOS POR DIA
//
//
//    }
//endregion

    fun toAdd(dto: UserDto) {
        users = users.plus(
            User(
                id = users.size.toLong() + 1,
                name = dto.name,
                email = dto.email,
                password = dto.password
            )
        )
    }
}