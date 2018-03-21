package bmi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BMIController extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("piyo");
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("bmi.fxml"));
		AnchorPane root = fxml.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
