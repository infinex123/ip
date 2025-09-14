package clover;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class DialogBox extends HBox {
    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

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

    public static DialogBox forUser(String text) {
        Image userImg = new Image(DialogBox.class.getResourceAsStream("/images/User.png"));
        return new DialogBox(text, userImg, false);
    }

    public static DialogBox forBot(String text) {
        Image botImg = new Image(DialogBox.class.getResourceAsStream("/images/Clover.png"));
        return new DialogBox(text, botImg, true);
    }
}
