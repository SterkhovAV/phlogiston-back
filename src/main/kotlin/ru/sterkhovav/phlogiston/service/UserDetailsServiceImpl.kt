package ru.sterkhovav.phlogiston.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.sterkhovav.phlogiston.dao.models.User
import ru.sterkhovav.phlogiston.dao.repository.UserRepository
import ru.sterkhovav.phlogiston.security.UserDetailsImpl

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.getByUsername(username) ?: throw UsernameNotFoundException("User not found")
        return UserDetailsImpl(user)
    }

    fun getUserStatus(username: String): Boolean {
        val status = userRepository.getByUsername(username)?.active ?: throw UsernameNotFoundException("User not found")
        return status
    }
}