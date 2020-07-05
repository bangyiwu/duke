package main.java;

import main.java.Task;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Storage store = new Storage();
        TaskList taskList = store.load();
        ArrayList<Task> todoList = taskList.todoList;
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
                            todoList) {
                        System.out.println( todoList.indexOf(task)+1 + ". " + task.toString());
                    }
                    if (todoList.size() == 0) {
                        System.out.println("Your life is empty now bruh");
                    }
                    break;
                case "done":
                    int taskID = sc.nextInt() - 1;
                    Task task = todoList.get(taskID);
                    task.markAsDone();
                    System.out.println("Gratz, you finished this dawg :");
                    System.out.println(task.toString());
                    break;
                case "todo":
                    String name = sc.nextLine();
                    try {
                        if (name.isEmpty()) {
                            throw new DukeException("no task indicated");
                        } else {
                            Todo td = new Todo(name);
                            todoList.add(td);
                            System.out.println("Aight new task for you: \n" + td.toString());
                            System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                            List<String> arrayList = new ArrayList<> ( Arrays.asList ( "a" , "b" , "c" ) );
                            store.write(td);

                        }
                    } catch (DukeException | IOException e){
                        System.out.println("Enter a task after todo!");
                    }
                    break;
                case "deadline":
                    String fullDL = sc.nextLine();
                    try {
                    String DLname = fullDL.split("/by")[0];
                    String DLtime = fullDL.split("/by")[1];
                    Deadline dl = new Deadline(DLname, DLtime);
                    todoList.add(dl);
                    System.out.println("Aight new task for you: \n" + dl.toString());
                    System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                    store.write(dl);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("You either didn't enter a date or a task");
                    }
                    break;
                case "event":
                    String fullE = sc.nextLine();
                    String EventName = fullE.split("/at")[0];
                    String EventTime = fullE.split("/at")[1];
                    Event e = new Event(EventName, EventTime);
                    todoList.add(e);
                    System.out.println("Aight new task for you: \n" + e.toString());
                    System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                    store.write(e);
                    break;
                case "delete":
                    int deleteID = sc.nextInt() - 1;
                    Task deleted = todoList.get(deleteID);
                    todoList.remove(deleteID);
                    store.overwrite(new TaskList(todoList));
                    System.out.println("Gotchu, I am removing \n" + deleted.toString());
                    System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
                    break;
                default:
                    System.out.println("whatchu talking about willis");
            }
        }
    }
}

