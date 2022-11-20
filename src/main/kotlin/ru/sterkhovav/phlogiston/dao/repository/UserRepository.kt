package ru.sterkhovav.phlogiston.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sterkhovav.phlogiston.dao.models.User
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getByUsername(username: String): User?
    fun getByEmail(email: String): User?
    fun getByUuid(uuid: UUID): User?
}