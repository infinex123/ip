package clover;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainWindow {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Clover bot;

    public void setBot(Clover bot) { this.bot = bot; }

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setOnAction(e -> handleUserInput());
    }

    public void greet() {
        addBotText(bot.getGreeting());
    }

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

    private void addUserText(String text) {
        dialogContainer.getChildren().add(DialogBox.forUser(text));
    }

    private void addBotText(String text) {
        dialogContainer.getChildren().add(DialogBox.forBot(text));
    }


}
