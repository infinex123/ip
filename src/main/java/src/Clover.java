import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Clover {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] items = new Task[100];
        int size = 0;


        String line = "______________________________________________________";
        System.out.println(line);
        System.out.println("Hello!, I'm Clover");
        System.out.println("What can I do for you?");
        System.out.println(line);
        //System.out.println("Bye. Hope to see you again soon!");
        //System.out.println(line);

        while (in.hasNextLine()) {
            String input =  in.nextLine().trim();
            Task task = new Task(input);
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye, hope to see you again soon!!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < size;i++) {
                    System.out.println(" " + (i + 1) + "." + items[i]);
                }
            } else if (input.toLowerCase().startsWith("mark ")) {
                markOrUnmark(items, input, true);
            } else if (input.toLowerCase().startsWith("unmark")) {
                markOrUnmark(items, input, false);
            } else if (!input.isEmpty()) {
                if (size < items.length) {
                    items[size++] = task;
                    System.out.println("added: " + input);
                } else {
                    System.out.println("full!!");
                }
            }
        }
    }

    private static void markOrUnmark(Task[] tasks, String input, boolean mark) {
        String[] parts = input.split("\\s+", 2);
        int index = Integer.parseInt(parts[1]) - 1;
        Task task = tasks[index];
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
