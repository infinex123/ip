package clover;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The main entry point of the Clover application.
 * Sets up storage, user interface, and task list, then runs the main loop.
 */

public class Clover {
    private final Ui ui = new Ui();
    private TaskList tasks;

    private static final Storage storage = new Storage("data", "duke.txt");


    private static final DateTimeFormatter[] LDT = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,           // 2019-12-02T18:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),  // 2019-12-02 1800
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm")     // 2/12/2019 1800
    };
    private static final DateTimeFormatter[] LD = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_LOCAL_DATE,                // 2019-12-02
            DateTimeFormatter.ofPattern("d/M/uuuu")          // 2/12/2019
    };

    private static class BufferingUi extends Ui {
        private final StringBuilder sb = new StringBuilder();

        @Override public void show(String msg) {
            sb.append(msg).append("\n");
        }
        @Override public void showError(String msg) {
            sb.append(msg).append("\n");
        }
        @Override public void showLine() {}

        String flush() {
            return sb.toString().stripTrailing();
        }
    }

    /**
     * Attempts to parse a date/time string in multiple supported formats.
     *
     * @param raw the raw user input string
     * @return the parsed LocalDateTime
     * @throws DukeException if the input cannot be parsed
     *
     */

    public static LocalDateTime parseFlexibleDateTime(String raw) throws DukeException {
        assert raw != null : "parseFlexibleDateTime: raw input cannot be null";
        String s = raw.trim();
        for (DateTimeFormatter f : LDT) {
            try {
                return LocalDateTime.parse(s, f);
            } catch (DateTimeParseException ignored) {

            }
        }
        for (DateTimeFormatter f : LD) {
            try {
                return LocalDate.parse(s, f).atStartOfDay();
            } catch (DateTimeParseException ignored) {

            }
        }
        throw new DukeException(
                "I couldn't understand the date/time.\nTry: 2019-12-02T18:00 | 2019-12-02 1800 | 2/12/2019 1800 | 2019-12-02 | 2/12/2019"
        );
    }

    public Clover() {
        try {
            this.tasks = new TaskList(storage.load());
            assert this.tasks != null : "Clover: TaskList must not be null after load()";
        } catch (Exception e) {
            ui.show("Error loading data file, starting with an empty list!");
            this.tasks = new TaskList();
            assert this.tasks != null : "Clover: TaskList must not be null after fallback init";
        }
    }

    public String getGreeting() {
        return "HELLO! I’m Clover.\nWhat can I do for you today?";
    }

    public String getResponse(String input) {
        assert input != null : "getResponse: input must not be null";
        BufferingUi bui = new BufferingUi();
        try {
            Command c = Parser.parse(input);
            assert c != null : "Parser should never return null";
            assert tasks != null : "TaskList must be initialized before executing";
            c.execute(tasks, bui, storage);  // Re-use your existing command flow
            if (c.isExit()) {
                // Optional: persist/cleanup already done by execute if needed
            }
        } catch (DukeException e) {
            bui.showError(e.getMessage());
        } catch (Exception e) {
            bui.showError("Unexpected error: " + e.getMessage());
        }
        return bui.flush();
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try { String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Unexpected error: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {

        new Clover().run();
    }

}

