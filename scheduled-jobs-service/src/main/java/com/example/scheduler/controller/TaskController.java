package com.example.scheduler.controller;


import com.example.scheduler.entity.TaskLog;
import com.example.scheduler.repository.TaskLogRepository;
import com.example.scheduler.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskLogRepository taskLogRepository;

    public TaskController(TaskService taskService, TaskLogRepository taskLogRepository) {
        this.taskService = taskService;
        this.taskLogRepository = taskLogRepository;
    }

    @GetMapping("/logs")
    public List<TaskLog> getAllLogs() {
        return taskLogRepository.findAll();
    }

    @PostMapping("/run")
    public String runManually(@RequestParam String task, @RequestParam(defaultValue = "300") int timeMs) {
        taskService.manualTrigger(task, timeMs);
        return "Executed manual task: " + task;
    }
    
    @GetMapping("/benchmark")
    public String compareManualVsScheduled(@RequestParam(defaultValue = "5") int iterations) {
        long manualTotal = 0;
        long scheduledTotal = 0;
        String task = "Benchmark-Comparison";

        for (int i = 0; i < iterations; i++) {
            long startManual = System.nanoTime();
            taskService.manualTrigger(task, 400);  // simulate manual delay
            manualTotal += (System.nanoTime() - startManual);

            long startScheduled = System.nanoTime();
            taskService.manualTrigger("Scheduled-" + task, 200);  // simulate precomputed/cached delay
            scheduledTotal += (System.nanoTime() - startScheduled);
        }

        double manualAvg = manualTotal / (1_000_000.0 * iterations);
        double scheduledAvg = scheduledTotal / (1_000_000.0 * iterations);
        double gain = ((manualAvg - scheduledAvg) / manualAvg) * 100.0;

        return String.format("Avg Manual: %.2f ms | Avg Scheduled: %.2f ms | Improvement: %.2f%%",
                manualAvg, scheduledAvg, gain);
    }

}