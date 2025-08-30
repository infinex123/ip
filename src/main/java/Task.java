public abstract class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
                t = new Deadline(desc, parts[3]);
                break;
            case "E":
                if (parts.length == 5) {
                    t = new Event(desc, parts[3], parts[4]); // your 3-param Event
                } else if (parts.length == 4) {
                    t = new Event(desc, parts[3], parts[3]);
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

