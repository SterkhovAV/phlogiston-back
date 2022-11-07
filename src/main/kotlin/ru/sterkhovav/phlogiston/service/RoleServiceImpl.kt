package ru.sterkhovav.phlogiston.service

import org.springframework.stereotype.Service
import ru.sterkhovav.phlogiston.dao.models.Role
import ru.sterkhovav.phlogiston.dao.repository.RoleRepository
import ru.sterkhovav.phlogiston.dto.RoleDto
import ru.sterkhovav.phlogiston.utils.UserRoleNotFoundException

interface RoleService {
    fun getRoleById(id: Long): Role
}

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository
) : RoleService {

    override fun getRoleById(id: Long): Role {
        return roleRepository.getRoleById(id) ?: throw UserRoleNotFoundException()
    }
}
