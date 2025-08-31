package clover;

class ExitCommand extends Command {
    @Override boolean isExit() {
        return true;
    }
    @Override void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}

