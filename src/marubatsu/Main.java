package marubatsu;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author tkato
 * @see https://ateitexe.com/javafx-output-message-on-button-click/
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Controller.fxml"));
		Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("testJava");
		primaryStage.setOnCloseRequest(event->{});
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}