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
                        System.out.println( todo.indexOf(task)+1 + ". " + task.toString());
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
                    System.out.println(task.toString());
                    break;
                case "todo":
                    String name = sc.nextLine();
                    try {
                        if (name.isEmpty() || name == " ") {
                            System.out.println("Retry that command with a task");
                            throw new IllegalArgumentException("no task indicated");
                        } else {
                            Todo td = new Todo(name);
                            todo.add(td);
                            System.out.println("Aight new task for you: \n" + td.toString());
                            System.out.println("Now you got " + todo.size() + " task(s) waiting man");
                        }} catch (IllegalArgumentException e){
                            if (name.isEmpty() || name == " ") {
                                throw new DukeException(
                                        "Enter that thingy you want to do", e
                                );
                            }
                    }
                    break;
                case "deadline":
                    String fullDL = sc.nextLine();
                    String DLname = fullDL.split("/by")[0];
                    String DLtime = fullDL.split("/by")[1];
                    Deadline dl = new Deadline(DLname, DLtime);
                    todo.add(dl);
                    System.out.println("Aight new task for you: \n" + dl.toString());
                    System.out.println("Now you got " + todo.size() + " task(s) waiting man");
                    break;
                case "event":
                    String fullE = sc.nextLine();
                    String EventName = fullE.split("/at")[0];
                    String EventTime = fullE.split("/at")[1];
                    Event e = new Event(EventName, EventTime);
                    todo.add(e);
                    System.out.println("Aight new task for you: \n" + e.toString());
                    System.out.println("Now you got " + todo.size() + " task(s) waiting man");
                    break;
                case "delete":
                    int deleteID = sc.nextInt() - 1;
                    Task deleted = todo.get(deleteID);
                    todo.remove(deleteID);
                    System.out.println("Hehe, I am removing this task: \n" + deleted.toString());
                    System.out.println("Now you got " + todo.size() + " task(s) waiting man");
                    break;
                default:
                    System.out.println("whatchu talking about willis");
            }
        }
    }
}
