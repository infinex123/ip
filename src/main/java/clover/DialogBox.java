package clover;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom control representing a dialog box consisting of a label and an image.
 * Used in the Clover GUI for displaying both user and bot messages.
 */
public class DialogBox extends HBox {
    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the given text, image, and flip flag.
     *
     * @param text the message text to display
     * @param img  the image (avatar) to display
     * @param flip whether to flip the dialog box (used for bot messages)
     */
    private DialogBox(String text, Image img, boolean flip) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxml.setController(this);
            fxml.setRoot(this);
            fxml.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dialog.setText(text);
        displayPicture.setImage(img);

        if (flip) {
            getChildren().remove(displayPicture);
            getChildren().add(0, displayPicture);
        }
    }

    /**
     * Creates a {@code DialogBox} for user messages.
     *
     * @param text the user message text
     * @return a {@code DialogBox} displaying the user's message
     */
    public static DialogBox forUser(String text) {
        Image userImg = new Image(DialogBox.class.getResourceAsStream("/images/User.png"));
        return new DialogBox(text, userImg, false);
    }

    /**
     * Creates a {@code DialogBox} for bot messages.
     *
     * @param text the bot message text
     * @return a {@code DialogBox} displaying the bot's message
     */
    public static DialogBox forBot(String text) {
        Image botImg = new Image(DialogBox.class.getResourceAsStream("/images/Clover.png"));
        return new DialogBox(text, botImg, true);
    }
}

