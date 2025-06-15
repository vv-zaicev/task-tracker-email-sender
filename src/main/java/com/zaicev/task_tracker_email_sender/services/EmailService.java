package com.zaicev.task_tracker_email_sender.services;

import com.zaicev.task_tracker_email_sender.models.EmailContext;

import jakarta.mail.MessagingException;

public interface EmailService {
	public void sendEmail(EmailContext emailContext) throws MessagingException;
}
