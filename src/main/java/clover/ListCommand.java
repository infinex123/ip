package clover;

class ListCommand extends Command {
    @Override void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.show("     " + (i + 1) + "." + tasks.get(i));
        }
    }
}