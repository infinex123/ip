package clover;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
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
