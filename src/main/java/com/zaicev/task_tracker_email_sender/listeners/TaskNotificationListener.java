package com.zaicev.task_tracker_email_sender.listeners;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.zaicev.task_tracker_email_sender.dto.TaskNotificationMessage;
import com.zaicev.task_tracker_email_sender.models.EmailContext;
import com.zaicev.task_tracker_email_sender.services.EmailService;

import jakarta.mail.MessagingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskNotificationListener {
	
	@Value("${TASK_NOTIFICATION_TOPIC}")
	@Getter
	private String taskNotificationTopic;

	private final EmailService emailService;

	private final Function<TaskNotificationMessage, EmailContext> taskNotificationMessageConverter;

	@KafkaListener(topics = "#{__listener.taskNotificationTopic}")
	public void listen(TaskNotificationMessage taskNotificationMessage) {
		EmailContext emailContext = taskNotificationMessageConverter.apply(taskNotificationMessage);
		try {
			emailService.sendEmail(emailContext);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		}
	}
}
