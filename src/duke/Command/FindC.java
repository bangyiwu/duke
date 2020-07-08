package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindC extends Command {

    public void execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        String keyword = ui.sc.nextLine();
        int findCount = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (Task find: todoList.todoList) {
            if (find.description.contains(keyword)) {
                System.out.println(findCount + 1 + find.toString());
                findCount++;
            }
        }
        if (findCount == 0) {
            System.out.println("No related tasks found");
        }
    }
}
