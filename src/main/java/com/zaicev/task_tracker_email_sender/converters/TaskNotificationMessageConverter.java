package com.zaicev.task_tracker_email_sender.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zaicev.task_tracker_email_sender.dto.TaskNotificationMessage;
import com.zaicev.task_tracker_email_sender.models.EmailContext;

import lombok.Setter;

@Component
public class TaskNotificationMessageConverter implements Function<TaskNotificationMessage, EmailContext> {
	
	private final String SUBJECT = "Ваша статистика за день на TaskTracker";

	@Setter
	@Value("${messages.templates.task-notification}")
	private String templateLocation;

	@Value("${EMAILBOX}")
	private String FROM;

	@Override
	public EmailContext apply(TaskNotificationMessage message) {
		Map<String, Object> context = new HashMap<>();
		context.put("username", message.username());
		context.put("completedTasks", message.completedTasks());
		context.put("activeTasks", message.activeTasks());
		return EmailContext.builder()
				.context(context)
				.from(FROM)
				.to(message.email())
				.subject(SUBJECT)
				.templateLocation(templateLocation)
				.build();
	}

}
