import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markDone() {
        this.isDone = true;
    }

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
                    t = new Event(desc, from, to); // your 3-param Event
                } else if (parts.length == 4) {
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    t = new Event(desc, from, from);
                } else {
                    throw new IllegalArgumentException("Invalid Event format: " + line);
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

