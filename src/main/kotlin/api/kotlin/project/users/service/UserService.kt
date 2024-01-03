package api.kotlin.project.users.service

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.mapper.UserFormMapper
import api.kotlin.project.users.mapper.UserViewMapper
import api.kotlin.project.users.model.User
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors

//Anotação para classes representantes de serviços
@Service

class UserService(
    private var users: List<User> = ArrayList(),
    private val userViewMapper: UserViewMapper,
    private val userFormMapper: UserFormMapper
) {
    //region init
    init {
        val user1 = User(
            id = 1,
            name = "Jhoy",
            email = "jhoymartins@gmail.com",
            password = "123456",
            date = LocalDate.parse("2023-12-31")
        )
        val user2 = User(
            id = 2,
            name = "João",
            email = "joaozinho1@gmail.com",
            password = "123456",
            date = LocalDate.parse("2024-01-03")
        )
        val user3 = User(
            id = 3,
            name = "Pedro",
            email = "pedrosantoss@gmail.com",
            password = "123456",
            date = LocalDate.parse("2024-01-02")
        )
        val user4 = User(
            id = 3,
            name = "Pedro",
            email = "pedrosantoss@gmail.com",
            password = "123456",
            date = LocalDate.parse("2024-01-03")
        )
        users = Arrays.asList(user1, user2, user3, user4)
    }
    //endregion

    fun allUsers(): List<UserView> {
        return users.stream().map { u ->
            userViewMapper.map(u)
        }.collect(Collectors.toList())
    }

    fun searchForId(id: Long): UserView {
        val user = users.stream().filter { u ->
            u.id == id
        }.findFirst().get()
        return userViewMapper.map(user)
    }

    fun create(form: NewUserForm) {
        val user = userFormMapper.map(form)
        user.id = users.size.toLong() + 1
        users = users.plus(user)
    }

    fun perDay(date: String?): Int {
        var dateParam = LocalDate.parse(date)

        val usersOnDate =
            users.filter { u ->
                u.date == dateParam
            }
        return usersOnDate.size.toInt()
    }

    fun today(): Int {
        val usersOnDate =
            users.filter { u ->
                u.date == LocalDate.now()
            }
        return usersOnDate.size.toInt()
    }
}