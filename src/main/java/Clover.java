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



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String line = "______________________________________________________";
        System.out.println(line);
        System.out.println("Hello!, I'm Clover");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (in.hasNextLine()) {
            String input =  in.nextLine().trim();
            Task task = new Task(input);
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye, hope to see you again soon!!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printList();
            } else if (input.startsWith("todo")) {
                String description = input.substring(5).trim();
                Task t = new ToDo(description);
                tasks.add(t);
                printAdded(t);
                continue;
            } else if (input.startsWith("deadline ")) {
                String arg = input.substring(9).trim();
                String[] parts = arg.split("/by", 2);
                String description = parts[0].trim();
                String by = parts.length > 1 ? parts[1].trim() : "";
                Task t = new Deadline(description, by);
                tasks.add(t);
                printAdded(t);
                continue;

            } else if (input.startsWith("event ")) {
                String arg = input.substring(6);
                String[] p1 = arg.split("/from",2);
                String desc = p1[0].trim();
                String[] p2 = p1[1].split("/to", 2);
                String from = p2[0].trim();
                String to = p2[1].trim();
                Task t = new Event(desc,from, to);
                tasks.add(t);
                printAdded(t);
                continue;

            } else if (input.toLowerCase().startsWith("mark ")) {
                markOrUnmark(tasks, input, true);
            } else if (input.toLowerCase().startsWith("unmark")) {
                markOrUnmark(tasks, input, false);
            } else if (!input.isEmpty()) {
                    tasks.add(task);
                    System.out.println("added: " + input);
                } else {
                    System.out.println("full!!");
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

