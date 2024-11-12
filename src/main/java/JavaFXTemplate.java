import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the initial Welcome screen FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/WelcomeScreen.fxml"));
			AnchorPane root = loader.load();

			// Set the initial scene with the Welcome screen
			Scene scene = new Scene(root, 1440, 830);

			// Set the title of the stage
			primaryStage.setTitle("Three Card Poker Game");

			// Set the scene and show the stage
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
