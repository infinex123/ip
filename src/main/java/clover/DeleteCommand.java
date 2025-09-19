package clover;

class DeleteCommand extends Command {
    private final String arg;
    public DeleteCommand(String arg) {

        this.arg = arg;
    }

    @Override void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(arg.trim());
            Task t = tasks.remove(index);
            ui.show("Okay, I've removed this task:");
            ui.show("   " + t);
            ui.show("Now you have " + (tasks.size() - 1) + " tasks in the list.");
            storage.save(tasks.asList());
        } catch (Exception e) {
            throw new DukeException("Invalid index for delete.");
        }
    }
}