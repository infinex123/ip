class MarkCommand extends Command {
    private final String arg;
    private final boolean mark;
    public MarkCommand(String arg, boolean mark) {
        this.arg = arg; this.mark = mark;
    }

    @Override void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(arg.trim());
            Task task = tasks.get(index);
            if (mark) {
                task.markDone();
                ui.show(" Nice! I've marked this task as done:");
            } else {
                task.markUndone();
                ui.show(" OK, I've marked this task as not done yet:");
            }
            ui.show("   " + task);
            storage.save(tasks.asList());
        } catch (Exception e) {
            throw new DukeException("Invalid index for mark/unmark.");
        }
    }
}