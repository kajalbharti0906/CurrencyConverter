public class CommandProcessor {
    private TaskManager taskManager;
    
    public CommandProcessor() {
        this.taskManager = new TaskManager();
    }
    
    public String processCommand(String commandLine) {
        try {
            if (commandLine == null || commandLine.trim().isEmpty()) {
                throw new IllegalArgumentException("Command cannot be empty");
            }
            
            String[] parts = commandLine.trim().split(",");
            String command = parts[0].trim();
            
            switch (command) {
                case "ADD_TASK":
                    return processAddTask(parts);
                case "MODIFY_TASK":
                    return processModifyTask(parts);
                case "REMOVE_TASK":
                    return processRemoveTask(parts);
                case "GET_TASK":
                    return processGetTask(parts);
                case "CHANGE_TASK_STATUS":
                    return processChangeTaskStatus(parts);
                case "LIST_TASK":
                    return processListTask(parts);
                case "GET_ACTIVITY_LOG":
                    return processGetActivityLog(parts);
                case "GET_STATISTICS":
                    return processGetStatistics(parts);
                default:
                    throw new IllegalArgumentException("Unknown command: " + command);
            }
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
    
    private String processAddTask(String[] parts) {
        if (parts.length != 5) {
            throw new IllegalArgumentException("ADD_TASK requires exactly 4 parameters: taskName, taskDescription, tags, deadline");
        }
        
        String taskName = parts[1].trim();
        String taskDescription = parts[2].trim();
        String tags = parts[3].trim();
        String deadline = parts[4].trim();
        
        return taskManager.addTask(taskName, taskDescription, tags, deadline);
    }
    
    private String processModifyTask(String[] parts) {
        if (parts.length != 5) {
            throw new IllegalArgumentException("MODIFY_TASK requires exactly 4 parameters: taskId, taskDescription, tags, deadline");
        }
        
        int taskId = Integer.parseInt(parts[1].trim());
        String taskDescription = parts[2].trim();
        String tags = parts[3].trim();
        String deadline = parts[4].trim();
        
        return taskManager.modifyTask(taskId, taskDescription, tags, deadline);
    }
    
    private String processRemoveTask(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("REMOVE_TASK requires exactly 1 parameter: taskId");
        }
        
        int taskId = Integer.parseInt(parts[1].trim());
        return taskManager.removeTask(taskId);
    }
    
    private String processGetTask(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("GET_TASK requires exactly 1 parameter: taskId");
        }
        
        int taskId = Integer.parseInt(parts[1].trim());
        return taskManager.getTask(taskId);
    }
    
    private String processChangeTaskStatus(String[] parts) {
        if (parts.length != 3) {
            throw new IllegalArgumentException("CHANGE_TASK_STATUS requires exactly 2 parameters: taskId, status");
        }
        
        int taskId = Integer.parseInt(parts[1].trim());
        String status = parts[2].trim();
        
        return taskManager.changeTaskStatus(taskId, status);
    }
    
    private String processListTask(String[] parts) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("LIST_TASK requires exactly 3 parameters: filterBy, sortKey, sortOrder");
        }
        
        String filterBy = parts[1].trim();
        String sortKey = parts[2].trim();
        String sortOrder = parts[3].trim();
        
        return taskManager.listTasks(filterBy, sortKey, sortOrder);
    }
    
    private String processGetActivityLog(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("GET_ACTIVITY_LOG requires exactly 1 parameter: taskId");
        }
        
        int taskId = Integer.parseInt(parts[1].trim());
        return taskManager.getActivityLog(taskId);
    }
    
    private String processGetStatistics(String[] parts) {
        if (parts.length != 1) {
            throw new IllegalArgumentException("GET_STATISTICS requires no parameters");
        }
        
        return taskManager.getStatistics();
    }
}