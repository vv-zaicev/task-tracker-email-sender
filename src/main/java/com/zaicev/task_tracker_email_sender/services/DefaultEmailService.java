package com.zaicev.task_tracker_email_sender.services;

import java.nio.charset.StandardCharsets;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.zaicev.task_tracker_email_sender.models.EmailContext;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService{
	
	private final JavaMailSender javaMailSender;
	
	private final SpringTemplateEngine templateEngine;

	@Override
	public void sendEmail(EmailContext emailContext) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		
		Context templateContext = new Context();
		templateContext.setVariables(emailContext.getContext());
		String emailContent = templateEngine.process(emailContext.getTemplateLocation(), templateContext);
		
		mimeMessageHelper.setTo(emailContext.getTo());
		mimeMessageHelper.setSubject(emailContext.getSubject());
		mimeMessageHelper.setFrom(emailContext.getFrom());
		mimeMessageHelper.setText(emailContent, true);
		
		javaMailSender.send(message);
	}
	
	
}
