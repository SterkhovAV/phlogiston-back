package ru.sterkhovav.phlogiston

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhlogistonApplication

fun main(args: Array<String>) {
	runApplication<PhlogistonApplication>(*args)
}
