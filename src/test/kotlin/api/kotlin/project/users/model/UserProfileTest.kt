package api.kotlin.project.users.model

import java.time.LocalDate

object UserProfileTest {
    fun build() = UserProfile(
        id = 1,
        name = "João da Silva",
        email = "joaodasilva@gmail.com",
        password = "123456",
        status = StatusUser.ATIVO,
        dateAdd = LocalDate.now()
    )
}