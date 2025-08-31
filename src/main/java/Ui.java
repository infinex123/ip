import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String line = "______________________________________________________";
        System.out.println(line);
        System.out.println("Hello!, I'm Clover");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void showLine() {
        System.out.println("______________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void show(String s) {
        System.out.println(s);
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showBye() {
        System.out.println("Bye, hope to see you again soon!!");
    }


}
