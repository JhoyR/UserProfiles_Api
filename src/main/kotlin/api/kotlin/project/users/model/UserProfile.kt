package api.kotlin.project.users.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class UserProfile(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var email: String,
    var password: String,
    @Enumerated(value = EnumType.STRING)
    var status: StatusUser = StatusUser.ATIVO,
    val dateAdd: LocalDate = LocalDate.now()
)