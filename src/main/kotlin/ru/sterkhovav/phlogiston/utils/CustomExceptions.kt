package ru.sterkhovav.phlogiston.utils

class UserNotCreatedException(val errors: List<String>): RuntimeException()
class UserRoleNotFoundException(val error: String = "User role not found"): RuntimeException()