abstract class Command {
    boolean isExit() {
        return false;
    }
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

