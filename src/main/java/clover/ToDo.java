package clover;

/**
 * Represents a simple task without deadlines or event times.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the given description.
     *
     * @param description the description of the todo task
     * @throws AssertionError if the description is null or empty
     */
    public ToDo(String description) {
        super(description);
        assert description != null : "ToDo description must not be null";
        assert !description.trim().isEmpty() : "ToDo description must not be empty";
    }

    /**
     * Converts this todo task to a string suitable for storage.
     *
     * @return the storage format string for this todo
     */
    @Override
    public String toStorageString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + this.getDescription();
    }

    /**
     * Returns a string representation of this todo task.
     *
     * @return the pretty-printed string of the todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

