import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Clover {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] items = new String[100];
        int size = 0;


        String line = "______________________________________________________";
        System.out.println(line);
        System.out.println("Hello!, I'm Clover");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (in.hasNextLine()) {
            String input = in.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("goodbye! hope to see you again!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < size; i++) {
                    System.out.println(" " + (i + 1) + "." + items[i]);
                }
            } else if (!input.isEmpty()) {
                if (size < items.length) {
                    items[size++] = input;
                    System.out.println("added: " + input);
                }
            } else {
                System.out.println(input);
            }
        }
    }
}
