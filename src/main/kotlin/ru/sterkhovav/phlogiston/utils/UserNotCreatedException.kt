package ru.sterkhovav.phlogiston.utils

class UserNotCreatedException(val errors: List<String>): RuntimeException() {

}