package com.example.scheduler.service;


import com.example.scheduler.entity.TaskLog;
import com.example.scheduler.repository.TaskLogRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskLogRepository repo;

    public TaskService(TaskLogRepository repo) {
        this.repo = repo;
    }

    private void simulateWork(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void execute(String taskName, int simulatedTimeMs) {
        long start = System.nanoTime();
        simulateWork(simulatedTimeMs);
        long duration = (System.nanoTime() - start) / 1_000_000;
        repo.save(new TaskLog(taskName, duration));
        System.out.println("Executed task: " + taskName + " in " + duration + "ms");
    }

    
    @Scheduled(cron = "0 */1 * * * *") // every 1 min
    public void syncUsers() {
        execute("Sync Users", 300);
    }

    @Scheduled(cron = "15 */1 * * * *")
    public void generateDailyReport() {
        execute("Daily Report", 500);
    }

    @Scheduled(cron = "30 */1 * * * *")
    public void retryFailedAlerts() {
        execute("Retry Alerts", 200);
    }

    @Scheduled(cron = "45 */1 * * * *")
    public void archiveOldLogs() {
        execute("Archive Logs", 400);
    }

    @Scheduled(cron = "0 */2 * * * *")
    public void refreshCache() {
        execute("Cache Refresh", 250);
    }

    @Scheduled(cron = "15 */2 * * * *")
    public void detectInactiveUsers() {
        execute("Inactive User Scan", 350);
    }

    @Scheduled(cron = "30 */2 * * * *")
    public void cleanTempData() {
        execute("Temp Cleanup", 300);
    }

    @Scheduled(cron = "45 */2 * * * *")
    public void generateWeeklyTrend() {
        execute("Weekly Trend", 600);
    }

    @Scheduled(cron = "0 */3 * * * *")
    public void emailDigest() {
        execute("Email Digest", 200);
    }

    @Scheduled(cron = "15 */3 * * * *")
    public void dbConsistencyCheck() {
        execute("DB Consistency", 500);
    }

    // Manual fallback
    public void manualTrigger(String taskName, int simulatedTimeMs) {
        execute("Manual - " + taskName, simulatedTimeMs);
    }
}