import java.util.Scanner;

public class Clover {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String line = "______________________________________________________";
        System.out.println(line);
        System.out.println("Hello!, I'm Clover");
        System.out.println("What can I do for you?");
        System.out.println(line);
        //System.out.println("Bye. Hope to see you again soon!");
        //System.out.println(line);

        while (in.hasNextLine()) {
            String userLine =  in.nextLine();
            if (userLine.equals("bye")) {
                System.out.println("Bye, hope to see you again soon!!");
                break;
            }

            System.out.println(userLine);
        }
    }
}
