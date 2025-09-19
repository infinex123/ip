package clover;

/**
 * Represents a command to exit the Clover application.
 */
class ExitCommand extends Command {

    /**
     * Indicates that this command will terminate the application.
     *
     * @return {@code true} since this command exits the app
     */
    @Override
    boolean isExit() {
        return true;
    }

    /**
     * Executes the ExitCommand, displaying the farewell message to the user.
     *
     * @param tasks   the TaskList (unused in this command)
     * @param ui      the Ui used to display the farewell message
     * @param storage the Storage (unused in this command)
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}


