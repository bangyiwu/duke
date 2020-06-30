package main.java;

import main.java.Task;

import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todo = new ArrayList<Task>();
        System.out.println("What's new scooby doo?\n" + "How can I help you today?");
        while (sc.hasNext()) {
            String command = sc.next();
            switch(command) {
                case "bye":
                    System.out.println("See you later alligator ");
                    System. exit(0);
                    break;
                case "list":
                    for ( Task task:
                            todo) {
                        System.out.println( todo.indexOf(task)+1 + ". " + task.getStatusIcon() + " " + task.description);
                    }
                    if (todo.size() == 0) {
                        System.out.println("Your life is empty now bruh");
                    }
                    break;
                case "done":
                    int taskID = sc.nextInt() - 1;
                    Task task = todo.get(taskID);
                    task.markAsDone();
                    System.out.println("Gratz, you finished this dawg :");
                    System.out.println(task.getStatusIcon()+ task.description);
                    break;
                default:
                    String fullTask = command + sc.nextLine();
                    todo.add(new Task(fullTask));
                    System.out.println("okay you need to: " + fullTask);
            }
        }
    }
}
