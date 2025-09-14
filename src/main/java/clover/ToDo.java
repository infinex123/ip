package clover;
/**
 * Represents a simple task without deadlines or event times.
 */

public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
        assert description != null : "ToDo description must not be null";
        assert !description.trim().isEmpty() : "ToDo description must not be empty";

    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
