package clover;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} has a description and a specific due date/time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    private static final DateTimeFormatter PRETTY =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"); // e.g., Dec 02 2019, 6:00 PM
    private static final DateTimeFormatter STORE =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Constructs a new {@code Deadline} task.
     *
     * @param description the description of the task
     * @param by the date and time by which the task is due
     * @throws AssertionError if the description is null/empty or if {@code by} is null
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Deadline description must be non-empty";
        assert by != null : "Deadline 'by' must not be null";
        this.by = by;
        assert this.by != null : "Deadline 'by' must be set";
    }

    /**
     * Returns the due date/time of this deadline task.
     *
     * @return the {@link LocalDateTime} representing when the task is due
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Converts this deadline task into a string suitable for storage.
     *
     * @return the storage format string for this deadline
     */
    public String toStorageString() {
        return "D | " + ( /* isDone? no getter now; extend clover.Task if needed */ false ? "1" : "0")
                + " | " + this.toString()
                + " | " + by.format(STORE);
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return the pretty-printed string of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(PRETTY) + ")";
    }
}
