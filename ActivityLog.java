import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityLog {
    public enum ActivityType {
        CREATED, MODIFIED, STATUS_CHANGED, REMOVED
    }
    
    public static class Activity {
        private ActivityType type;
        private LocalDateTime timestamp;
        
        public Activity(ActivityType type) {
            this.type = type;
            this.timestamp = LocalDateTime.now();
        }
        
        public ActivityType getType() { return type; }
        public LocalDateTime getTimestamp() { return timestamp; }
    }
    
    private Map<Integer, List<Activity>> taskActivities;
    
    public ActivityLog() {
        this.taskActivities = new HashMap<>();
    }
    
    public void logActivity(int taskId, ActivityType activityType) {
        taskActivities.computeIfAbsent(taskId, k -> new ArrayList<>())
                     .add(new Activity(activityType));
    }
    
    public List<String> getActivityLog(int taskId) {
        List<Activity> activities = taskActivities.getOrDefault(taskId, new ArrayList<>());
        List<String> activityTypes = new ArrayList<>();
        
        for (Activity activity : activities) {
            activityTypes.add(activity.getType().toString());
        }
        
        return activityTypes;
    }
    
    public void removeTaskActivities(int taskId) {
        // Keep the activities for removed tasks as per requirement
        // The log should still show CREATED, MODIFIED, REMOVED
    }
}