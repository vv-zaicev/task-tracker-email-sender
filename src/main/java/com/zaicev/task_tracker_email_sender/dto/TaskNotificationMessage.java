package com.zaicev.task_tracker_email_sender.dto;

import java.util.List;

public record TaskNotificationMessage(String email, String username, List<TaskDTO> completedTasks, List<TaskDTO> activeTasks) {

}
