package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteC extends Command {
    public void execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        int deleteID = ui.sc.nextInt() - 1;
        Task deleted = todoList.get(deleteID);
        todoList.deleteTask(deleteID);
        store.overwrite(todoList);
        System.out.println("Gotchu, I am removing \n" + deleted.toString());
        System.out.println("Now you got " + todoList.size() + " task(s) waiting man");
    }
}
