package clover;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a command to remind the user of upcoming tasks
 * that are due within a specified number of days.
 */
public class ReminderCommand extends Command {
    private int days;

    /**
     * Constructs a {@code ReminderCommand} with the given argument.
     * <p>
     * If the argument is {@code null}, the default is 1 day.
     *
     * @param arg the number of days ahead to check for tasks (as a string)
     * @throws DukeException if the argument cannot be parsed as an integer
     */
    public ReminderCommand(String arg) throws DukeException {
        if (arg == null) {
            this.days = 1;
        } else {
            try {
                this.days = Integer.parseInt(arg.trim());
            } catch (Exception e) {
                throw new DukeException("Usage: remind [days] (e.g., remind 3)");
            }
        }
    }

    /**
     * Executes the ReminderCommand by showing tasks that are due within
     * the next {@code days} days from the current time.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui object used to display reminders
     * @param storage the Storage (unused in this command)
     * @throws DukeException if an error occurs during execution
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoff = now.plusDays(days);

        List<Task> list = tasks.asList();
        ui.show("     You need to do these tasks within " + days + " day(s):");
        int shown = 0;
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            LocalDateTime due = extractDueTime(t);
            if (due != null && !due.isBefore(now) && !due.isAfter(cutoff)) {
                ui.show("       " + (i + 1) + ". " + t);
                shown++;
            }
        }
        if (shown == 0) {
            ui.show("       (Yay! no tasks due)");
        }
    }

    /**
     * Extracts the due date/time from a task if it is a Deadline or Event.
     *
     * @param t the task to extract from
     * @return the due {@link LocalDateTime} of the task, or {@code null} if not applicable
     */
    private static LocalDateTime extractDueTime(Task t) {
        try {
            if (t instanceof Deadline) {
                return ((Deadline) t).getBy();
            }
            if (t instanceof Event) {
                return ((Event) t).getFrom();
            }
        } catch (Exception ignore) {}
        return null;
    }
}
