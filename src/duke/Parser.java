package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The Parser class comprehends and carries out the user commands
 * It is used in the Ui class
 */

public class Parser {
    public  Parser(){}

    /**
     * Constructor for loading deadlines using switch and case
     * @param sc            the scanner used to take in the user commands
     * @param todoList      the array list the stores all the todos
     * @param store         the Storage that handles the loading and saving of tasks into the schedule text file
     */
    public void parse(Scanner sc, ArrayList<Task> todoList, Storage store) throws IOException {
        String command = sc.next();
        switch (command) {
            case "bye":
                System.out.println("See you later alligator ");
                System.exit(0);
                break;
            case "list":
                for (Task task :
                        todoList) {
                    System.out.println(todoList.indexOf(task) + 1 + ". " + task.toString());
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
                        System.out.println("Now you got " + todoList.size() +
                                " task(s) waiting man");
                        List<String> arrayList = new ArrayList<>(Arrays.asList("a", "b", "c"));
                        store.write(td);

                    }
                } catch (DukeException | IOException e) {
                    System.out.println("Enter a task after schedule!");
                }
                break;
            case "deadline":
                String fullDL = sc.nextLine();
                try {
                    String dlName = fullDL.split("/by")[0];
                    LocalDate dlTime = LocalDate.parse(fullDL.split("/by ")[1]);
                    Deadline dl = new Deadline(dlName, dlTime);
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
                String eventName = fullE.split("/at")[0];
                LocalDate eventTime = LocalDate.parse(fullE.split("/at ")[1]);
                Event e = new Event(eventName, eventTime);
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
            case "on":
                LocalDate checkOn = LocalDate.parse(sc.nextLine().trim());
                System.out.println("On this day, you have: ");
                int eventCount = 0;
                for (Task checkEvent : todoList) {
                    if (checkEvent instanceof Event && ((Event) checkEvent).at.equals(checkOn)) {
                        System.out.println(checkEvent.toString());
                        eventCount++;
                    }
                }
                if (eventCount > 0) {
                    System.out.println("   [ A total of " + eventCount + " event(s)]");
                } else {
                    System.out.println("   [ You have no events on this day ]");
                }
                break;
            case "by":
                LocalDate checkBy = LocalDate.parse(sc.nextLine().trim());
                System.out.println("By this day, you have: ");
                int deadCount = 0;
                for (Task checkDead : todoList) {
                    if (checkDead instanceof Deadline &&
                            (((Deadline) checkDead).by.isBefore(checkBy) ||
                            ((Deadline) checkDead).by.equals(checkBy))) {
                        System.out.println(checkDead.toString());
                        deadCount++;
                    }
                }
                if (deadCount > 0) {
                    System.out.println("   [ A total of " + deadCount + " deadline(s)]");
                } else {
                    System.out.println("   [ You have no deadlines by this day ]");
                }
                break;
            case "find":
                String keyword = sc.nextLine();
                int findCount = 0;
                System.out.println("Here are the matching tasks in your list:");
                for (Task find: todoList) {
                    if (find.description.contains(keyword)) {
                        System.out.println(findCount + 1 + find.toString());
                        findCount++;
                    }
                }
                if (findCount == 0) {
                    System.out.println("No related tasks found");
                }
                break;
            default:
                System.out.println("whatchu talking about willis");
        }
    }
}
