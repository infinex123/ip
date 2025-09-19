package clover;

/**
 * A launcher class to start the Clover application.
 * <p>
 * This is a workaround required for JavaFX applications to avoid
 * issues with classpath setups when launching directly from {@code MainApp}.
 */
public class Launcher {

    /**
     * The main entry point of the application.
     *
     * @param args the command-line arguments passed to the program
     */
    public static void main(String[] args) {
        MainApp.launch(MainApp.class, args);
    }
}
