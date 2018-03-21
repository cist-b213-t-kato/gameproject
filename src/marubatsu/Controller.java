package marubatsu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller {

	private int[][] board = {
			{ 0, 0, 0 },
			{ 0, 0, 0 },
			{ 0, 0, 0 }
	};

	private int turn = 1;

	@FXML
	private GridPane gridPane;

	@FXML
	private void initialize() {
		for ( int i=0; i<3; i++ ) {
			for ( int j=0; j<3; j++ ) {
				ImageView imageView = new ImageView(new Image("marubatsu/empty.gif"));
				imageView.setFitWidth(64);
				imageView.setFitHeight(64);
				String posStr = i + "," + j;
				final int m = i;
				final int n = j;
				imageView.setOnMouseClicked(event->{
					System.out.println(posStr);
					if ( turn == 1 ) {
						board[m][n] = 1;
						imageView.setImage(new Image("marubatsu/landolt.png"));
					} else if ( turn == 2 ) {
						board[m][n] = 2;
						imageView.setImage(new Image("marubatsu/batsu.jpg"));
					}
					turn = turn % 2 + 1;
				});
				imageView.setStyle("-fx-alignment:center;");
				gridPane.add(imageView, i, j);
			}
		}
		gridPane.setGridLinesVisible(true);

//		Label label = new Label("o");
//		label.setStyle("-fx-alignment:center; -fx-font-size:64px;");
//
//		label.setOnMouseClicked(event->{
//			label.setText("x");
//		});
//		gridPane.add(label, 1, 1);
	}

	@FXML
	protected void onButtonClick(ActionEvent event) {
		System.out.println("クリックされた");
	}

}
