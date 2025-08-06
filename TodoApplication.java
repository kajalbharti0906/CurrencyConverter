import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TodoApplication {
    private CommandProcessor commandProcessor;
    
    public TodoApplication() {
        this.commandProcessor = new CommandProcessor();
    }
    
    public void runInteractiveMode() {
        System.out.println("TODO-LIST Application Started");
        System.out.println("Enter commands (type 'exit' to quit):");
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                
                String result = commandProcessor.processCommand(line);
                System.out.println(result);
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
        
        System.out.println("TODO-LIST Application Ended");
    }
    
    public void runTestCases() {
        System.out.println("Running sample test cases from the problem statement:");
        System.out.println("=".repeat(60));
        
        List<String> testCommands = new ArrayList<>();
        
        // Sample inputs from the problem statement
        testCommands.add("ADD_TASK,LLD-1,SDE-2 Problem Statements,ENGINEERING+PRODUCT,2023-09-20");
        testCommands.add("ADD_TASK,LLD-2,UI/UX,DESIGN+PRODUCT,2023-09-12");
        testCommands.add("ADD_TASK,LLD-3,SDE-3 Problem Statements,ENGINEERING,2023-09-10");
        testCommands.add("ADD_TASK,MENTOR,product management,PRODUCT,2023-09-14");
        
        testCommands.add("MODIFY_TASK,1,null,null,2023-09-13");
        testCommands.add("MODIFY_TASK,2,UI/UX FIGMA,null,2023-09-20");
        testCommands.add("MODIFY_TASK,3,Engineering Problem Statement,null,null");
        
        testCommands.add("GET_TASK,1");
        
        testCommands.add("CHANGE_TASK_STATUS,2,IN_PROGRESS");
        testCommands.add("CHANGE_TASK_STATUS,3,COMPLETED");
        testCommands.add("CHANGE_TASK_STATUS,4,COMPLETED");
        
        testCommands.add("LIST_TASK,COMPLETED,DEADLINE,ASC");
        testCommands.add("LIST_TASK,IN_PROGRESS,DEADLINE,ASC");
        
        testCommands.add("GET_ACTIVITY_LOG,2");
        testCommands.add("REMOVE_TASK,1");
        testCommands.add("GET_ACTIVITY_LOG,1");
        
        testCommands.add("GET_STATISTICS");
        
        // Execute test commands
        for (String command : testCommands) {
            System.out.println("INPUT: " + command);
            String result = commandProcessor.processCommand(command);
            System.out.println("OUTPUT: " + result);
            System.out.println();
        }
        
        System.out.println("=".repeat(60));
        System.out.println("Test cases completed");
    }
    
    public static void main(String[] args) {
        TodoApplication app = new TodoApplication();
        
        if (args.length > 0 && "test".equals(args[0])) {
            // Run test cases if "test" argument is provided
            app.runTestCases();
        } else {
            // Run interactive mode by default
            app.runInteractiveMode();
        }
    }
}