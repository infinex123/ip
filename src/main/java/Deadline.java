public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + this.by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
