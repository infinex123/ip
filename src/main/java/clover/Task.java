package clover;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and completion status.
 */


public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }


    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }


    /**
     * Marks this task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

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
                    t = new Event(desc, from, to); // your 3-param clover.Event
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

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


    public String toStorageString() {
        throw new UnsupportedOperationException("Subclass must implement");
    }
}

