package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox root = new VBox(1);

		GridPane gridPane = new GridPane();

		gridPane.add(new Text("hoge"), 0, 0);
		gridPane.add(new Text("piyo"), 0, 1);
		gridPane.add(new Text("fuga"), 1, 0);

		Image image = new Image("img/landolt.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(32);
		imageView.setFitHeight(32);
		Button button = new Button("", imageView);


		gridPane.add(button, 1, 1);

		root.getChildren().add(gridPane);

		Scene scene = new Scene(root, 300, 300, Color.web("9FCC7F"));

		primaryStage.setScene(scene);
		primaryStage.show();

	}


}
