package api.kotlin.project.users.repository

import api.kotlin.project.users.model.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface UserRepository: JpaRepository<UserProfile, Long> {

    fun findByDateAdd(date: LocalDate): Long
}