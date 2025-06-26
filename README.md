# Scheduled Jobs Service (Spring Boot + H2)

This microservice automates 10+ recurring backend tasks using Spring Boot's `@Scheduled` functionality. It logs each task's execution time, simulates real-world backend operations such as data syncing, report generation, retries, and includes a benchmark to compare scheduled vs manual execution times.

## Tech Stack

- Java 17  
- Spring Boot 3.x  
- Spring Scheduler (`@Scheduled`)  
- Spring Data JPA + H2  
- REST API (for triggering and benchmarking tasks)

## Features

- 10+ backend tasks automated via cron-based scheduling  
- Execution logs stored in H2 database with timestamp and duration  
- REST endpoints to trigger tasks manually  
- Benchmark endpoint to compare manual vs scheduled execution performance  
- Simulates real operations: syncing, reporting, cleanup, retry mechanisms

## Scheduled Jobs

| Task                   | Frequency      | Description                              |
|------------------------|----------------|------------------------------------------|
| Sync Users             | Every 1 min    | Simulates syncing user logs or cache     |
| Generate Daily Report  | Every 1 min    | Creates user activity report             |
| Retry Failed Alerts    | Every 1 min    | Retries failed notifications             |
| Archive Old Logs       | Every 1 min    | Deletes outdated records                 |
| Refresh Cache          | Every 2 min    | Refreshes in-memory config/cache         |
| Detect Inactive Users  | Every 2 min    | Identifies unused records                |
| Clean Temp Data        | Every 2 min    | Cleans up temporary rows                 |
| Weekly Trend Report    | Every 2 min    | Simulates BI pipeline job                |
| Email Digest Generator | Every 3 min    | Sends email digest to users              |
| DB Consistency Check   | Every 3 min    | Verifies table row counts                |

## REST API

### View All Task Logs  
`GET /api/tasks/logs`  
Returns execution history from the database.

### Manually Trigger a Task  
`POST /api/tasks/run?task=TaskName&timeMs=300`  
Triggers a task manually with a simulated processing delay.

### Benchmark Scheduled vs Manual  
`GET /api/tasks/benchmark?iterations=5`  
Compares execution time of manual vs simulated scheduled tasks.

**Example Response:**  
`Avg Manual: 410.72 ms | Avg Scheduled: 217.58 ms | Improvement: 47.02%`

## Author

Gaurav Solanki  
GitHub: [https://github.com/GauravS0001](https://github.com/GauravS0001)
