package api.kotlin.project.users.dto

import api.kotlin.project.users.model.StatusUser
import java.time.LocalDate

//Informações que serão enviadas ao cliente
data class UserView (
    val id: Long? = null,
    val name: String,
    val email: String,
    val status: StatusUser,
    val dateAdd: LocalDate
)
