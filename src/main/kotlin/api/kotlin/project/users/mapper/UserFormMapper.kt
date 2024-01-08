package api.kotlin.project.users.mapper

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.model.UserProfile
import org.springframework.stereotype.Component

@Component
class UserFormMapper : Mapper<NewUserForm, UserProfile> {
    override fun map(t: NewUserForm): UserProfile {
        return UserProfile(
            name = t.name,
            email = t.email,
            password = t.password
        )
    }
}
