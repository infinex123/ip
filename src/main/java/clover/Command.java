package clover;

/**
 * Represents an abstract command that can be executed in the Clover application.
 * Each command encapsulates an action to be performed on the task list.
 */
abstract class Command {

    /**
     * Returns whether this command signals the application to exit.
     * <p>
     * By default, commands do not exit the application.
     *
     * @return {@code true} if this command should exit the app, {@code false} otherwise
     */
    boolean isExit() {
        return false;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   the TaskList that stores the tasks
     * @param ui      the Ui used to interact with the user
     * @param storage the Storage used to save tasks persistently
     * @throws DukeException if an error occurs during execution
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

