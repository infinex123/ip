public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
