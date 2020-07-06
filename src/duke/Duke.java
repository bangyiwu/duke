package duke;

import java.io.*;
import java.text.ParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public void run() throws IOException {
        this.ui.uiRun(this.tasks, this.storage);
    }
    public static void main(String[] args) throws IOException, ParseException {
        new Duke("src/duke/text/todo").run();
    }
}

