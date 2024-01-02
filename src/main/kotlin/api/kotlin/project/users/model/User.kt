package api.kotlin.project.users.model

import java.time.LocalDateTime

data class User (
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String,
    val status: StatusUser = StatusUser.ATIVO,
    val date: LocalDateTime = LocalDateTime.now()
)