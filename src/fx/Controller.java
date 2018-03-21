package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

	@FXML
	protected void onButtonClick(ActionEvent event) {
		System.out.println("クリックされた");
	}

}
