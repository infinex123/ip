class AddEventCommand extends Command {
    private final String arg;
    public AddEventCommand(String arg) {
        this.arg = arg;
    }

    @Override void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String s = arg == null ? "" : arg.trim();
        if (s.isEmpty()) throw new DukeException("Event format: event <desc> /from <start> /to <end>");

        int f = s.indexOf("/from");
        int t = s.indexOf("/to");
        if (f < 0 || t < 0 || t <= f) throw new DukeException("Need both '/from' and '/to'. Example: event meetup /from Mon 2pm /to 4pm");

        String desc = s.substring(0, f).trim();
        String fromRaw = s.substring(f + 5, t);
        String toRaw = s.substring(t + 3).trim();
        if (desc.isEmpty()) throw new DukeException("Event needs a description before '/from'.");
        if (fromRaw.isEmpty()) throw new DukeException("Provide a start time after '/from'.");
        if (toRaw.isEmpty()) throw new DukeException("Provide an end time after '/to'.");

        var from = Clover.parseFlexibleDateTime(fromRaw);
        var to = Clover.parseFlexibleDateTime(toRaw);

        Task task = new Event(desc, from, to);
        tasks.add(task);
        storage.save(tasks.asList());
        ui.show("     Got it. I've added this task:");
        ui.show("       " + task);
        ui.show("     Now you have " + tasks.size() + " tasks in the list.");
    }
}