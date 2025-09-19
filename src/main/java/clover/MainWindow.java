package clover;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window of the Clover JavaFX application.
 * <p>
 * Responsible for handling user input, displaying dialog boxes, and
 * interacting with the {@link Clover} bot.
 */
public class MainWindow {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Clover bot;

    /**
     * Injects the Clover bot instance into this controller.
     *
     * @param bot the {@link Clover} instance to be used for generating responses
     */
    public void setBot(Clover bot) {
        this.bot = bot;
    }

    /**
     * Initializes the main window UI components after the FXML is loaded.
     * Sets up scrolling behavior and binds the Enter key to send input.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setOnAction(e -> handleUserInput());
    }

    /**
     * Displays the greeting message from the bot in the dialog container.
     */
    public void greet() {
        addBotText(bot.getGreeting());
    }

    /**
     * Handles user input from the text field when the user presses Enter
     * or clicks the send button. Sends input to the bot and displays
     * both user and bot dialog boxes.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }
        addUserText(input);
        String response = bot.getResponse(input);
        addBotText(response);

        userInput.clear();
        userInput.requestFocus();
    }

    /**
     * Adds a user dialog box with the given text to the dialog container.
     *
     * @param text the user input text to display
     */
    private void addUserText(String text) {
        dialogContainer.getChildren().add(DialogBox.forUser(text));
    }

    /**
     * Adds a bot dialog box with the given text to the dialog container.
     *
     * @param text the bot response text to display
     */
    private void addBotText(String text) {
        dialogContainer.getChildren().add(DialogBox.forBot(text));
    }
}

