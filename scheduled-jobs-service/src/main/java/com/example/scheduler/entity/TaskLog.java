package com.example.scheduler.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TaskLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private long executionTimeMs;
    private LocalDateTime executedAt;

    public TaskLog() {}

    public TaskLog(String taskName, long executionTimeMs) {
        this.taskName = taskName;
        this.executionTimeMs = executionTimeMs;
        this.executedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public long getExecutionTimeMs() {
		return executionTimeMs;
	}

	public void setExecutionTimeMs(long executionTimeMs) {
		this.executionTimeMs = executionTimeMs;
	}

	public LocalDateTime getExecutedAt() {
		return executedAt;
	}

	public void setExecutedAt(LocalDateTime executedAt) {
		this.executedAt = executedAt;
	}

    
}