package Services;

import Model.Task;

import java.util.Scanner;

public class ConsoleService {
    private final Scanner scanner = new Scanner(System.in);

    public int mainMenuPrompt(){
        System.out.println("What do you want to do? Please select a number listed below: ");
        System.out.println("1: View Tasks");
        System.out.println("2: Add a Task");
        System.out.println("3: Delete a Task");
        System.out.println("4: Exit");
        return scanner.nextInt();
    }

    public Task addTaskMenu(){
        Task taskToAdd = null;
        while(!scanner.nextLine().equals("0")) {
            System.out.println("What is your task? \n **enter 0 to return to main menu**");
            String taskName = scanner.nextLine();
            System.out.println("What is your task's category? (CODE, PROFESSIONAL, MISC) \n **enter 0 to return to main menu**");
            String category = scanner.nextLine().toUpperCase();
            System.out.println("What day of the week is your task? mo/tu/we/th/fr/sa/su \n **enter 0 to return to main menu**");
            String day = scanner.nextLine().toLowerCase();
            taskToAdd = new Task(taskName, category, day);
        }
        return taskToAdd;
    }

    public int deleteTaskMenu(){
        System.out.println("Select task to delete by number");
        return scanner.nextInt();
    }
}

