package api.kotlin.project.users.model

import api.kotlin.project.users.dto.UserView
import java.time.LocalDate

object UserProfileViewTest {
    fun build() = UserView(
        id = 1,
        name = "João da Silva",
        email = "joaodasilva@gmail.com",
        status = StatusUser.ATIVO,
        dateAdd = LocalDate.now()
    )
}
