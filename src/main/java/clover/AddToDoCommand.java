package clover;

/**
 * Represents a command to add a new {@link ToDo} task.
 */
class AddToDoCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddToDoCommand} with the given description.
     *
     * @param description the description of the todo task
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddToDoCommand to add a new ToDo task.
     *
     * @param tasks   the TaskList where the new task will be added
     * @param ui      the Ui object used to display output to the user
     * @param storage the Storage object used to save tasks
     * @throws DukeException if the description is null or empty
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String d = description == null ? "" : description.trim();
        if (d.isEmpty()) {
            throw new DukeException("todo needs a description!!");
        }
        Task t = new ToDo(d);
        tasks.add(t);
        storage.save(tasks.asList());
        ui.show("     Got it. I've added this task:");
        ui.show("       " + t);
        int TasksSize = tasks.size() - 1;
        ui.show("     Now you have " + TasksSize + " tasks in the list.");
    }
}
