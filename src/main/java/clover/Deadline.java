package clover;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter PRETTY =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"); // e.g., Dec 02 2019, 6:00 PM
    private static final DateTimeFormatter STORE =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String toStorageString() {
        return "D | " + ( /* isDone? no getter now; extend clover.Task if needed */ false ? "1" : "0")
                + " | " + this.toString()
                + " | " + by.format(STORE);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(PRETTY) + ")";
    }
}
