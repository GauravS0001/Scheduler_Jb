package com.example.scheduler.repository;

import com.example.scheduler.entity.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskLogRepository extends JpaRepository<TaskLog, Long> {
}