package clover;

/**
 * Represents a command to list all tasks in the task list.
 */
class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying all tasks currently in the task list.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui object used to display the list to the user
     * @param storage the Storage (unused in this command)
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("     Here are the tasks in your list:");
        for (int i = 1; i < tasks.size(); i++) {
            ui.show("     " + (i) + "." + tasks.get(i));
        }
    }
}
