package com.zaicev.task_tracker_email_sender.listeners;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.zaicev.task_tracker_email_sender.dto.EmailVerificationMessage;
import com.zaicev.task_tracker_email_sender.models.EmailContext;
import com.zaicev.task_tracker_email_sender.services.EmailService;

import jakarta.mail.MessagingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailConfirmationListener {

	@Value("${VEREFICATION_TOPIC}")
	@Getter
	private String mailVerificationTopic;

	private final EmailService emailService;

	private final Function<EmailVerificationMessage, EmailContext> emailVerificationMessageConverter;

	@KafkaListener(topics = "#{__listener.mailVerificationTopic}")
	public void listen(EmailVerificationMessage mailVerificationMessage) {
		EmailContext emailContext = emailVerificationMessageConverter.apply(mailVerificationMessage);
		try {
			emailService.sendEmail(emailContext);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
	}
}
