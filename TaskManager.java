import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private Map<Integer, Task> tasks;
    private int nextTaskId;
    private ActivityLog activityLog;
    private Statistics statistics;
    private static final Set<String> VALID_TAGS = Set.of("DESIGN", "ENGINEERING", "PRODUCT");
    
    public TaskManager() {
        this.tasks = new HashMap<>();
        this.nextTaskId = 1;
        this.activityLog = new ActivityLog();
        this.statistics = new Statistics();
    }
    
    public String addTask(String name, String description, String tagsStr, String deadlineStr) {
        try {
            // Check if task with same name already exists in OPEN or IN_PROGRESS status
            if (isTaskNameExists(name)) {
                throw new IllegalArgumentException("Task with name '" + name + "' already exists in OPEN or IN_PROGRESS status");
            }
            
            // Parse tags
            List<String> tags = parseTags(tagsStr);
            
            // Parse deadline
            LocalDate deadline = parseDeadline(deadlineStr);
            
            // Create task
            Task task = new Task(nextTaskId, name, description, tags, deadline);
            tasks.put(nextTaskId, task);
            
            // Log activity and update statistics
            activityLog.logActivity(nextTaskId, ActivityLog.ActivityType.CREATED);
            statistics.incrementAdded();
            
            return "TASK_ADDED " + nextTaskId++;
            
        } catch (Exception e) {
            throw new RuntimeException("Error adding task: " + e.getMessage(), e);
        }
    }
    
    public String modifyTask(int taskId, String description, String tagsStr, String deadlineStr) {
        try {
            Task task = tasks.get(taskId);
            if (task == null) {
                throw new IllegalArgumentException("Task with ID " + taskId + " not found");
            }
            
            if (task.getStatus() == Task.TaskStatus.COMPLETED) {
                throw new IllegalArgumentException("Cannot modify completed task");
            }
            
            boolean modified = false;
            
            // Update description if not null
            if (description != null && !description.equals("null")) {
                task.setDescription(description);
                modified = true;
            }
            
            // Update tags if not null
            if (tagsStr != null && !tagsStr.equals("null")) {
                List<String> tags = parseTags(tagsStr);
                task.setTags(tags);
                modified = true;
            }
            
            // Update deadline if not null
            if (deadlineStr != null && !deadlineStr.equals("null")) {
                LocalDate deadline = parseDeadline(deadlineStr);
                task.setDeadline(deadline);
                modified = true;
            }
            
            if (modified) {
                activityLog.logActivity(taskId, ActivityLog.ActivityType.MODIFIED);
                statistics.incrementModified();
            }
            
            return "TASK_MODIFIED " + taskId;
            
        } catch (Exception e) {
            throw new RuntimeException("Error modifying task: " + e.getMessage(), e);
        }
    }
    
    public String removeTask(int taskId) {
        try {
            Task task = tasks.get(taskId);
            if (task == null) {
                throw new IllegalArgumentException("Task with ID " + taskId + " not found");
            }
            
            String taskName = task.getName();
            tasks.remove(taskId);
            
            activityLog.logActivity(taskId, ActivityLog.ActivityType.REMOVED);
            statistics.incrementRemoved();
            
            return "TASK_REMOVED " + taskName;
            
        } catch (Exception e) {
            throw new RuntimeException("Error removing task: " + e.getMessage(), e);
        }
    }
    
    public String getTask(int taskId) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task with ID " + taskId + " not found");
        }
        return task.getName();
    }
    
    public String changeTaskStatus(int taskId, String statusStr) {
        try {
            Task task = tasks.get(taskId);
            if (task == null) {
                throw new IllegalArgumentException("Task with ID " + taskId + " not found");
            }
            
            Task.TaskStatus newStatus = Task.TaskStatus.valueOf(statusStr);
            String taskName = task.getName();
            
            task.setStatus(newStatus);
            activityLog.logActivity(taskId, ActivityLog.ActivityType.STATUS_CHANGED);
            statistics.incrementStatusChanged();
            
            // Note: Keep completed tasks in the list for LIST_TASK functionality
            // Tasks are not removed when marked as completed
            
            return "TASK_STATUS " + taskName + " " + statusStr;
            
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("No enum constant")) {
                throw new RuntimeException("Invalid status: " + statusStr);
            }
            throw new RuntimeException("Error changing task status: " + e.getMessage(), e);
        }
    }
    
    public String listTasks(String filterBy, String sortKey, String sortOrder) {
        try {
            Task.TaskStatus filterStatus = Task.TaskStatus.valueOf(filterBy);
            
            List<Task> filteredTasks = tasks.values().stream()
                    .filter(task -> task.getStatus() == filterStatus)
                    .collect(Collectors.toList());
            
            // Sort tasks
            if ("DEADLINE".equals(sortKey)) {
                if ("ASC".equals(sortOrder)) {
                    filteredTasks.sort(Comparator.comparing(Task::getDeadline));
                } else {
                    filteredTasks.sort(Comparator.comparing(Task::getDeadline).reversed());
                }
            }
            
            List<String> taskNames = filteredTasks.stream()
                    .map(Task::getName)
                    .collect(Collectors.toList());
            
            return taskNames.toString();
            
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid filter status: " + filterBy, e);
        }
    }
    
    public String getActivityLog(int taskId) {
        List<String> activities = activityLog.getActivityLog(taskId);
        return activities.toString();
    }
    
    public String getStatistics() {
        return statistics.getStatistics();
    }
    
    private boolean isTaskNameExists(String name) {
        return tasks.values().stream()
                .anyMatch(task -> task.getName().equals(name) && 
                         (task.getStatus() == Task.TaskStatus.OPEN || 
                          task.getStatus() == Task.TaskStatus.IN_PROGRESS));
    }
    
    private List<String> parseTags(String tagsStr) {
        if (tagsStr == null || tagsStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Tags cannot be empty");
        }
        
        String[] tagArray = tagsStr.split("\\+");
        List<String> tags = new ArrayList<>();
        
        for (String tag : tagArray) {
            String trimmedTag = tag.trim();
            if (!VALID_TAGS.contains(trimmedTag)) {
                throw new IllegalArgumentException("Invalid tag: " + trimmedTag + ". Valid tags are: " + VALID_TAGS);
            }
            tags.add(trimmedTag);
        }
        
        return tags;
    }
    
    private LocalDate parseDeadline(String deadlineStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(deadlineStr.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid deadline format. Expected yyyy-MM-dd, got: " + deadlineStr);
        }
    }
}