# TODO-LIST Application

A comprehensive task management application implemented in Java that allows users to add, modify, remove, and track tasks with deadlines and tags.

## Features

- **Task Management**: Add, modify, and remove tasks
- **Status Tracking**: Tasks can be OPEN, IN_PROGRESS, or COMPLETED
- **Tag Support**: Tasks can be tagged with DESIGN, ENGINEERING, or PRODUCT
- **Deadline Management**: Tasks have deadlines in yyyy-MM-dd format
- **Activity Logging**: Track when tasks are created, modified, status changed, or removed
- **Statistics**: View counts of added, removed, modified, and status changed tasks
- **Task Listing**: List tasks by status with sorting by deadline

## Compilation and Execution

### Compile the application:
```bash
javac *.java
```

### Run the application:

**Interactive Mode (default):**
```bash
java TodoApplication
```

**Test Mode (runs sample test cases):**
```bash
java TodoApplication test
```

**Edge Case Testing:**
```bash
java EdgeCaseTest
```

## Supported Commands

### ADD_TASK
Add a new task to the system.
```
ADD_TASK,<taskName>,<taskDescription>,<tags>,<deadline>
```
- Tags are separated by '+' (e.g., ENGINEERING+PRODUCT)
- Deadline format: yyyy-MM-dd
- Default status: OPEN

### MODIFY_TASK
Modify an existing task.
```
MODIFY_TASK,<taskId>,<taskDescription>,<tags>,<deadline>
```
- Use "null" for parameters you don't want to change

### REMOVE_TASK
Remove a task from the system.
```
REMOVE_TASK,<taskId>
```

### GET_TASK
Get the name of a specific task.
```
GET_TASK,<taskId>
```

### CHANGE_TASK_STATUS
Change the status of a task.
```
CHANGE_TASK_STATUS,<taskId>,<status>
```
- Valid statuses: OPEN, IN_PROGRESS, COMPLETED

### LIST_TASK
List tasks filtered by status and sorted by deadline.
```
LIST_TASK,<filterBy>,<sortKey>,<sortOrder>
```
- filterBy: OPEN, IN_PROGRESS, COMPLETED
- sortKey: DEADLINE
- sortOrder: ASC, DESC

### GET_ACTIVITY_LOG
Get the activity log for a specific task.
```
GET_ACTIVITY_LOG,<taskId>
```

### GET_STATISTICS
Get overall statistics.
```
GET_STATISTICS
```

## Example Usage

```
ADD_TASK,LLD-1,SDE-2 Problem Statements,ENGINEERING+PRODUCT,2023-09-20
MODIFY_TASK,1,null,null,2023-09-13
CHANGE_TASK_STATUS,1,IN_PROGRESS
LIST_TASK,IN_PROGRESS,DEADLINE,ASC
GET_ACTIVITY_LOG,1
GET_STATISTICS
```

## Business Rules

1. Task names must be unique for OPEN and IN_PROGRESS tasks
2. Valid tags: DESIGN, ENGINEERING, PRODUCT
3. Completed tasks remain in the system for listing and statistics
4. All operations are tracked in activity logs
5. Statistics track total counts across all operations

## Error Handling

The application gracefully handles various error conditions:
- Duplicate task names
- Invalid tags or date formats
- Non-existent task IDs
- Invalid status values
- Malformed commands

## Architecture

The application follows a modular design:
- `Task`: Model class representing a task
- `TaskManager`: Core business logic
- `ActivityLog`: Activity tracking functionality
- `Statistics`: Statistics tracking
- `CommandProcessor`: Command parsing and execution
- `TodoApplication`: Main application with interactive and test modes