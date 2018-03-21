package marubatsu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller {

	@FXML
	private GridPane gridPane;

	@FXML
	private void initialize() {
		System.out.println("nekoneko");
		Image image = new Image("marubatsu/landolt.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(32);
		imageView.setFitHeight(32);
		gridPane.add(imageView, 1, 1);
	}

	@FXML
	protected void onButtonClick(ActionEvent event) {
		System.out.println("クリックされた");
	}

}
