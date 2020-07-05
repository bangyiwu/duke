package main.java;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {

    public void write(Task task) throws IOException {
        FileWriter todoWriter = new FileWriter("src/main/java/todo", true);
        todoWriter.write(task.splitToString());
        todoWriter.close();
    }

    public TaskList load() throws IOException, ParseException {
        ArrayList<Task> todoList = new ArrayList<Task>();
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/todo"));
        String line = null;
        while((line = reader.readLine()) != null) {
            String[] taskArr = line.split("/");
            if (taskArr[0].equals("T")) {
                todoList.add(new Todo(taskArr[2], taskArr[1]));
            } else if (taskArr[0].equals("E")) {
//                System.out.println(taskArr[0]);
                todoList.add(new Event(taskArr[1], taskArr[2], LocalDate.parse(taskArr[3])));
            } else if (taskArr[0].equals("D")) {
//                System.out.println(taskArr[6]);
                todoList.add(new Deadline(taskArr[1], taskArr[2], LocalDate.parse(taskArr[3])));
            }
        }
        return new TaskList(todoList);
    }

    public void overwrite(TaskList tl) throws IOException {
        FileWriter todoWriter = new FileWriter("src/main/java/todo", false);
        for (Task task: tl.todoList
             ) {
            todoWriter.write(task.splitToString());
        }
        todoWriter.close();
    }

}