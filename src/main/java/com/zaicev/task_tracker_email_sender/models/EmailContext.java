package com.zaicev.task_tracker_email_sender.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailContext {
	private String from;
	private String to;
	private String subject;
	private String templateLocation;
	private Map<String, Object> context;
}
