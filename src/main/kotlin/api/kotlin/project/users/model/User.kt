package api.kotlin.project.users.model

import java.time.LocalDate

data class User (
    var id: Long? = null,
    val name: String,
    val email: String,
    val password: String,
    val status: StatusUser = StatusUser.ATIVO,
    val date: LocalDate = LocalDate.now()
)