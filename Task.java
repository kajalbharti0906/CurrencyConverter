import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Task {
    public enum TaskStatus {
        OPEN, IN_PROGRESS, COMPLETED
    }
    
    private int id;
    private String name;
    private String description;
    private List<String> tags;
    private LocalDate deadline;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    
    public Task(int id, String name, String description, List<String> tags, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.deadline = deadline;
        this.status = TaskStatus.OPEN;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getTags() { return tags; }
    public LocalDate getDeadline() { return deadline; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastModifiedAt() { return lastModifiedAt; }
    
    // Setters
    public void setDescription(String description) {
        this.description = description;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    public void setTags(List<String> tags) {
        this.tags = tags;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    public void setStatus(TaskStatus status) {
        this.status = status;
        this.lastModifiedAt = LocalDateTime.now();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id == task.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Task{id=%d, name='%s', status=%s, deadline=%s}", 
                           id, name, status, deadline);
    }
}