package clover;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task scheduled over a period of time with a start and end.
 */

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter PRETTY =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter STORE =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toStorageString() {
        return "E | " + (isDone()? "1" : "0")
                + " | " + this.getDescription()
                + " | " + from.format(STORE) + " | " + to.format(STORE);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(PRETTY)
                + " to: " + to.format(PRETTY) + ")";
    }

}
