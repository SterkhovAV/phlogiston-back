package ru.sterkhovav.phlogiston.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sterkhovav.phlogiston.dao.models.Role

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun getRoleById(id:Long):Role?
}