package com.zaicev.task_tracker_email_sender.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zaicev.task_tracker_email_sender.dto.EmailVerificationMessage;
import com.zaicev.task_tracker_email_sender.models.EmailContext;

import lombok.Setter;

@Component
public class EmailVerificationMessageConverter implements Function<EmailVerificationMessage, EmailContext> {

	

	private final String SUBJECT = "Подвердите учетную запись на TaskTracker";

	@Setter
	@Value("${messages.templates.mail-confirmation}")
	private String templateLocation;
	
	@Value("${EMAILBOX}")
	private String FROM;

	@Override
	public EmailContext apply(EmailVerificationMessage message) {
		Map<String, Object> context = new HashMap<>();
		context.put("username", message.username());
		context.put("expirationTimeMinutes", message.expirationTimeMinutes());
		context.put("code", message.code());
		return EmailContext.builder()
				.context(context)
				.from(FROM)
				.to(message.email())
				.subject(SUBJECT)
				.templateLocation(templateLocation)
				.build();

	}

}
