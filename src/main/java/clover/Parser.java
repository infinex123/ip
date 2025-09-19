package clover;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String in = input == null ? "" : input.trim();
        if (in.isEmpty()) return new Command() {
            @Override void execute(TaskList tasks, Ui ui, Storage storage) {}
        };

        String[] parts = in.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1] : "";

        switch (cmd) {
            case "bye": return new ExitCommand();
            case "list": return new ListCommand();
            case "mark": return new MarkCommand(arg, true);
            case "unmark": return new MarkCommand(arg, false);
            case "delete": return new DeleteCommand(arg);
            case "todo": return new AddToDoCommand(arg);
            case "deadline": return new AddDeadlineCommand(arg);
            case "event": return new AddEventCommand(arg);
            case "find" : return new FindCommand(arg);
            case "remind": return new ReminderCommand(arg);
            default: throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }
}
