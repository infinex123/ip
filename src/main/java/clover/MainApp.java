package clover;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main JavaFX application class for Clover.
 * <p>
 * Responsible for setting up the primary stage, loading the FXML layout,
 * and initializing the {@link MainWindow} controller with a {@link Clover} instance.
 */
public class MainApp extends Application {

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage for this application
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(fxml.load());
        stage.setScene(scene);
        stage.setTitle("Clover");
        stage.show();

        MainWindow controller = fxml.getController();
        controller.setBot(new Clover());
        controller.greet(); // show greeting bubble
    }
}

