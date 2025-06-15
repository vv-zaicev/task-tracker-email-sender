package com.zaicev.task_tracker_email_sender.dto;

public record EmailVerificationMessage(String email, String username, String code, int expirationTimeMinutes) {

}
