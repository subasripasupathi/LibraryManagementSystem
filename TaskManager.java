import java.util.*;

class Task {
    private String title;
    private String dueDate;
    private boolean isCompleted;

    public Task(String title, String dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[ " + (isCompleted ? "✔" : "✗") + " ] " + title + (dueDate.isEmpty() ? "" : " (Due: " + dueDate + ")");
    }
}

public class TaskManager {
    private static List<Task> taskList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        if (!login()) {
            System.out.println("Login failed. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\nTask Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List All Tasks");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. View Pending Tasks");
            System.out.println("6. View Completed Tasks");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    listTasks();
                    break;
                case 4:
                    markTaskCompleted();
                    break;
                case 5:
                    viewPendingTasks();
                    break;
                case 6:
                    viewCompletedTasks();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter due date (optional, press enter to skip): ");
        String dueDate = scanner.nextLine();
        taskList.add(new Task(title, dueDate));
        System.out.println("Task added successfully!");
    }

    private static void removeTask() {
        listTasks();
        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\nTask List:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    private static void markTaskCompleted() {
        listTasks();
        System.out.print("Enter task number to mark as completed: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < taskList.size()) {
            taskList.get(index).markCompleted();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void viewPendingTasks() {
        System.out.println("\nPending Tasks:");
        for (Task task : taskList) {
            if (!task.isCompleted()) {
                System.out.println(task);
            }
        }
    }

    private static void viewCompletedTasks() {
        System.out.println("\nCompleted Tasks:");
        for (Task task : taskList) {
            if (task.isCompleted()) {
                System.out.println(task);
            }
        }
    }
}
