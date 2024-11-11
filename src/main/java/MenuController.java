import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public void onStartButtonPressed(ActionEvent event) {
        try {
            // Load the GamePlay scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePlayScreen.fxml"));
            BorderPane gamePlayRoot = loader.load();

            // Set the scene to the new GamePlay scene
            Scene gamePlayScene = new Scene(gamePlayRoot, 1440, 800);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(gamePlayScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Handle the Exit button click

    public void handleExitButton(ActionEvent event) {
        // Exit the application
        System.exit(0);
    }
}
