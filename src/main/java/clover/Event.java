package clover;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task scheduled over a period of time with a start and end.
 * An {@code Event} has a description, a start {@code LocalDateTime}, and an end {@code LocalDateTime}.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    private static final DateTimeFormatter PRETTY =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter STORE =
            DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Constructs a new {@code Event}.
     *
     * @param description the description of the event
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the {@link LocalDateTime} when the event starts
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Converts this event to a string suitable for storage.
     *
     * @return the storage format string for this event
     */
    public String toStorageString() {
        return "E | " + (isDone() ? "1" : "0")
                + " | " + this.getDescription()
                + " | " + from.format(STORE) + " | " + to.format(STORE);
    }

    /**
     * Returns a string representation of this event.
     *
     * @return the pretty-printed string of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(PRETTY)
                + " to: " + to.format(PRETTY) + ")";
    }
}
