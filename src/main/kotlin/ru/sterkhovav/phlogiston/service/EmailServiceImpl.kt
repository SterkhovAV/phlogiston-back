package ru.sterkhovav.phlogiston.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component


interface EmailService {
    fun sendMimeMessage(to: String, subject: String, text: String)
}

@Component
class EmailServiceImpl(
    private val javaMailSender: JavaMailSender
) : EmailService {

    override fun sendMimeMessage(to: String, subject: String, text: String) {
        val message =  javaMailSender.createMimeMessage();
        val messageHelper = MimeMessageHelper(message, true);
        messageHelper.setFrom("Phlogiston <sterkhovav18@gmail.com>")
        messageHelper.setTo(to)
        messageHelper.setSubject(subject)
        messageHelper.setText(text)
        javaMailSender.send(message)
    }
}