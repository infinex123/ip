class AddToDoCommand extends Command {
    private final String description;
    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String d = description == null ? "" : description.trim();
        if (d.isEmpty()) throw new DukeException("todo needs a description!!");
        Task t = new ToDo(d);
        tasks.add(t);
        storage.save(tasks.asList());
        ui.show("     Got it. I've added this task:");
        ui.show("       " + t);
        ui.show("     Now you have " + tasks.size() + " tasks in the list.");
    }
}
