package clover;
import java.time.LocalDateTime;
import java.util.List;

public class ReminderCommand extends Command {
    private int days;

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
     * Extracts a datetime from Deadline or Event tasks.
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
