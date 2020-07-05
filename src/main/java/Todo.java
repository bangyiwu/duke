package main.java;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, String done) {
        super(description);
        this.isDone = (done == "1");
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String splitToString() {
        return "\n" + "T" + "/" + this.ifDone() + "/" + this.description;
    }
}
