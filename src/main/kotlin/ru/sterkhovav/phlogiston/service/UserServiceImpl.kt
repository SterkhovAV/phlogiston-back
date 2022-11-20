package ru.sterkhovav.phlogiston.service

import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.sterkhovav.phlogiston.controllers.ACCOUNT_ACTIVATION_API
import ru.sterkhovav.phlogiston.controllers.BASE_URL
import ru.sterkhovav.phlogiston.dao.models.User
import ru.sterkhovav.phlogiston.dao.repository.UserRepository
import ru.sterkhovav.phlogiston.dto.UserDto
import ru.sterkhovav.phlogiston.dto.UserRegistrationRequestDto
import ru.sterkhovav.phlogiston.utils.UserNotCreatedException
import java.util.*
import java.util.regex.Pattern


interface UserService {
    fun getUserByUsername(username: String): UserDto
    fun validateUser(userRegistrationRequest: UserRegistrationRequestDto)
    fun validateUsername(username: String): Boolean
    fun validateEmail(email: String): Boolean
    fun register(userRegistrationRequest: UserRegistrationRequestDto)
    fun activateUser(activation_code: UUID)
}

@Service
class UserServiceImpl(
    private val emailServiceImpl: EmailServiceImpl,
    private val userRepository: UserRepository,
    private val roleServiceImpl: RoleServiceImpl,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserService {

    override fun getUserByUsername(username: String): UserDto {
        return userRepository.getByUsername(username)?.toDto() ?: throw UsernameNotFoundException("User not found")
    }

    override fun validateUser(userRegistrationRequest: UserRegistrationRequestDto) {
        val errors = mutableListOf<String>()
        val emailRegex =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        val emailMatcher = emailRegex.matcher(userRegistrationRequest.email)
        if (!emailMatcher.find()) errors.add("Email is not valid")
        if (userRegistrationRequest.email.length > 45) errors.add("Email is too long (max size = 45)")

        if (userRegistrationRequest.lastName.length > 50 || userRegistrationRequest.lastName.isEmpty()) {
            errors.add("Last name is too long (max size = 50) or empty")
        }
        if (userRegistrationRequest.firstName.length > 50 || userRegistrationRequest.firstName.isEmpty()) {
            errors.add("First name is too long (max size = 50) or empty")
        }
        if (userRegistrationRequest.middleName != null) {
            if (userRegistrationRequest.middleName.length > 50) {
                errors.add("First name is too long (max size = 50)")
            }
        }
        if (userRegistrationRequest.organisation != null) {
            if (userRegistrationRequest.organisation.length > 500) {
                errors.add("organisation name is too long (max size = 500)")
            }
        }
        if (userRegistrationRequest.position != null) {
            if (userRegistrationRequest.position.length > 70) {
                errors.add("position name is too long (max size = 70)")
            }
        }
        if (userRegistrationRequest.phone != null) {
            if (userRegistrationRequest.phone.length > 50) {
                errors.add("position name is too long (max size = 50)")
            }
            val phoneRegex = Pattern.compile("^[0-9[\\)\\(\\-\\+]]+$")
            val phoneMatcher = phoneRegex.matcher(userRegistrationRequest.phone)
            if (!phoneMatcher.find()) errors.add("Phone number is not valid")
        }

        if (errors.isNotEmpty()) throw UserNotCreatedException(errors)

    }


    @Transactional
    override fun register(userRegistrationRequest: UserRegistrationRequestDto) {
        val errors = mutableListOf<String>()
        if (userRegistrationRequest.username.length > 30 || userRegistrationRequest.username.isEmpty()) {
            errors.add("Username is too long (max size = 30) or empty")
        }
        if (validateUsername(userRegistrationRequest.username)) errors.add("User with this name exists")
        if (validateEmail(userRegistrationRequest.email)) errors.add("User with this email exists")
        if (errors.isNotEmpty()) throw UserNotCreatedException(errors)
        validateUser(userRegistrationRequest)
        val uuid = UUID.randomUUID()
        userRepository.save(userRegistrationRequest.toEntity(roleServiceImpl.getRoleById(2), uuid))
        emailServiceImpl.sendMimeMessage(
            userRegistrationRequest.email,
            "Your account activation link",
            "$BASE_URL$ACCOUNT_ACTIVATION_API?activation_code=$uuid"
        )
        if (errors.isNotEmpty()) throw UserNotCreatedException(errors)
    }

    override fun validateUsername(username: String): Boolean {
        return userRepository.getByUsername(username) != null
    }

    override fun validateEmail(email: String): Boolean {
        return userRepository.getByEmail(email) != null
    }

    override fun activateUser(activation_code: UUID) {
        val user: User = userRepository.getByUuid(activation_code) ?: throw UsernameNotFoundException("User not found")
        println("sdsdsdsd")
        user.active = true
        userRepository.save(user)
    }


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    fun changePassword(user: User, newPassword: String?) {
//        user.password = bCryptPasswordEncoder.encode(newPassword)
//        user.lastTimeUpdatePassword = OffsetDateTime.now()
//        userRepository.save(user)
//    }
}


