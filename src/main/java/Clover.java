import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Clover {
    private static final List<Task> tasks = new ArrayList<>();

    private static final void printAdded(Task t) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void printList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    private static void printError(String msg) {
        System.out.println(msg);
    }

    private static void handle(String input) throws DukeException {;
        if (input.isEmpty()) {
            return;
        } else if (input.equalsIgnoreCase("list")) {
            printList();
        } else if (input.toLowerCase().startsWith("mark ")) {
            markOrUnmark(tasks, input, true);
        } else if (input.toLowerCase().startsWith("unmark")) {
            markOrUnmark(tasks, input, false);
        } else if (input.startsWith("todo")) {
            String description = input.length() > 4 ? input.substring(5).trim(): "";
            if (description.isEmpty()) {
                throw new DukeException("todo needs a description!!");
            }
            Task t = new ToDo(description);
            tasks.add(t);
            printAdded(t);
            return;
        }  else if (input.startsWith("deadline")) {
            String arg = input.length() > 8? input.substring(8).trim(): "";
            if (arg.isEmpty()) {
                throw new DukeException("Missing task description!!");
            }
            int at = arg.indexOf("/by");
            if (at < 0) {
                throw new DukeException("Missing '/by'. Eg. deadline return book /by Sunday");
            }

            String description = arg.substring(0, at).trim();
            String by = arg.substring(at + 3).trim();
            if (description.isEmpty()) {
                throw new DukeException("Missing task description!!");
            }
            if (by.isEmpty()) {
                throw new DukeException("Please let me know when it's due after '/by'.");
            }
            Task t = new Deadline(description, by);
            tasks.add(t);
            printAdded(t);
            return;
        }  else if (input.startsWith("event")) {
            String arg = input.length() > 4 ? input.substring(4).trim() : "";
            if (arg.isEmpty()) {
                throw new DukeException("Event format: event <desc> /from <start> /to <end>");
            }

            int f = arg.indexOf("/from:");
            int t = arg.indexOf("/to");
            if (f < 0 || t < 0 || t <= f) {
                throw new DukeException("Need both '/from' and '/to'. Example: event meetup /from Mon 2pm /to 4pm");
            }
            String desc = arg.substring(0, f).trim();
            String from = arg.substring(f + 5, t);
            String to = arg.substring(t + 3).trim();
            if (desc.isEmpty()) {
                throw new DukeException("Event needs a description before '/from'.");
            }

            if (from.isEmpty()) {
                throw new DukeException("Provide a start time after '/from'.");
            }
            if (to.isEmpty()) {
                throw new DukeException("Provide an end time after '/to'.");
            }

            Task task = new Event(desc,from, to);
            tasks.add(task);
            printAdded(task);
            return;

        } else if (input.startsWith("delete")) {
            String[] parts = input.split("\\s+", 2);
            int index = Integer.parseInt(parts[1]) - 1;
            Task t = tasks.get(index);
            tasks.remove(index);
            System.out.println("Okay, I've removed this task:");
            System.out.println("   " + t);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }

    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String line = "______________________________________________________";
        System.out.println(line);
        System.out.println("Hello!, I'm Clover");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (in.hasNextLine()) {
            String input = in.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye, hope to see you again soon!!");
                break;
            }
            try {
                handle(input);
            } catch (DukeException e) {
                printError(e.getMessage());
            }
        }
    }

    private static void markOrUnmark(List<Task> tasks, String input, boolean mark) {
        String[] parts = input.split("\\s+", 2);
        int index = Integer.parseInt(parts[1]) - 1;
        Task task = tasks.get(index);
        if (mark) {
            task.markDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + task);
        } else {
            task.markUndone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + task);
        }
    }
}

