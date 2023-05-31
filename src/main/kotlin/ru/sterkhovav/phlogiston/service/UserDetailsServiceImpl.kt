package ru.sterkhovav.phlogiston.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.sterkhovav.phlogiston.dao.repository.UserRepository
import ru.sterkhovav.phlogiston.security.UserDetailsImpl
import java.util.UUID

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.getByUsername(username) ?: throw UsernameNotFoundException("User not found")
        return UserDetailsImpl(user)
    }

    fun getUserStatus(username: String): Boolean {
        return userRepository.getByUsername(username)?.active ?: throw UsernameNotFoundException("User not found")
    }

    fun getUserEmail(username: String): String {
        return userRepository.getByUsername(username)?.email ?: throw UsernameNotFoundException("User not found")
    }

    fun getUserUUID(username: String): UUID {
        return userRepository.getByUsername(username)?.uuid ?: throw UsernameNotFoundException("User not found")
    }
}