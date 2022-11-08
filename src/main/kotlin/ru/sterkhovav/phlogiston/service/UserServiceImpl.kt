package ru.sterkhovav.phlogiston.service

import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.sterkhovav.phlogiston.dao.models.User
import ru.sterkhovav.phlogiston.dao.repository.UserRepository
import ru.sterkhovav.phlogiston.dto.UserDto
import ru.sterkhovav.phlogiston.dto.UserRegistrationRequestDto
import ru.sterkhovav.phlogiston.utils.UserNotCreatedException

interface UserService {
    fun getUserByUsername(username: String): UserDto
    fun validateUser(user: UserRegistrationRequestDto)
    fun validateUsername(username: String): Boolean
    fun validateEmail(Email: String): Boolean
    fun register(userRegistrationRequestDto: UserRegistrationRequestDto)
}

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleServiceImpl: RoleServiceImpl
) : UserService {

    override fun getUserByUsername(username: String): UserDto {
        return userRepository.getByUsername(username)?.toDto() ?: throw UsernameNotFoundException("User not found")
    }

    override fun validateUser(user: UserRegistrationRequestDto) {
        val errors = mutableListOf<String>()
        if (validateUsername(user.username)) errors.add("User with this name exists")
        if (validateEmail(user.email)) errors.add("User with this email exists")
        if (errors.isNotEmpty()) throw UserNotCreatedException(errors)

    }

    override fun validateUsername(username: String): Boolean {
        return userRepository.getByUsername(username) != null
    }

    override fun validateEmail(Email: String): Boolean {
        return userRepository.getByEmail(Email) != null
    }

    @Transactional
    override fun register(userRegistrationRequestDto: UserRegistrationRequestDto) {
        userRepository.save(
            userRegistrationRequestDto.toEntity(
                roleServiceImpl.getRoleById(userRegistrationRequestDto.roleId.toLong())
            )
        )
    }
}


