package clover;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and completion status.
 * <p>
 * Subclasses include {@link ToDo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     * The task is marked as not done by default.
     *
     * @param description the description of the task
     * @throws AssertionError if the description is null or empty
     */
    public Task(String description) {
        assert description != null : "Task description must not be null";
        assert !description.trim().isEmpty() : "Task description must not be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task has been marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon representing the completion state of the task.
     *
     * @return "X" if the task is done, otherwise a blank space
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.isDone = true;
        assert this.isDone : "Task should be marked done";
    }

    /**
     * Marks this task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
        assert !this.isDone : "Task should be marked not done";
    }

    /**
     * Creates a {@code Task} from a storage string.
     * <p>
     * Supports ToDo (T), Deadline (D), and Event (E) formats.
     *
     * @param line the storage string
     * @return the parsed Task
     * @throws IllegalArgumentException if the string is invalid or cannot be parsed
     */
    public static Task fromStorageString(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Too few fields: " + line);
        }

        String type = parts[0];
        String done = parts[1];
        String desc = parts[2];

        Task t;
        switch (type) {
        case "T":
            if (parts.length != 3) throw new IllegalArgumentException("Invalid T: " + line);
            t = new ToDo(desc);
            break;
        case "D":
            if (parts.length != 4) throw new IllegalArgumentException("Invalid D: " + line);
            LocalDateTime by = LocalDateTime.parse(parts[3]);
            t = new Deadline(desc, by);
            break;
        case "E":
            if (parts.length == 5) {
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                t = new Event(desc, from, to);
            } else if (parts.length == 4) {
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                t = new Event(desc, from, from);
            } else {
                throw new IllegalArgumentException("Invalid clover.Event format: " + line);
            }
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if ("1".equals(done)) {
            t.isDone = true;
        } else if (!"0".equals(done)) {
            throw new IllegalArgumentException("Bad done flag: " + done);
        }

        return t;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return the string form of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts this task to a string suitable for storage.
     * <p>
     * Each subclass must implement its own format.
     *
     * @return the storage format string
     * @throws UnsupportedOperationException if not implemented in a subclass
     */
    public String toStorageString() {
        throw new UnsupportedOperationException("Subclass must implement");
    }
}


