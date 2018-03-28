package gomoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller {

	private static int columnMax = 15;
	private static int rowMax = 15;

	private int[][] board = new int[columnMax][rowMax];

	private int turn = 1;

	@FXML
	private GridPane gridPane;

	private static final int[] vecX = { 1, 1, 0, -1 };
	private static final int[] vecY = { 0, 1, 1, 1 };
	private static final int[] sig = { 1, -1 };

	private int winner = 0;

	public int judge( int column, int row, int turn ) {
		for ( int i=0; i<4; i++ ) {
			int count = 0;

			for ( int s=0; s<2; s++ ) {
				for ( int l=1; ; l++ ) {
					int y = column + sig[s] * vecY[i] * l;
					int x = row + sig[s] * vecX[i] * l;
					if ( y<0 || y>=columnMax || x<0 || x>=rowMax ) {
						break;
					}
					if ( board[y][x] == turn ) {
						count++;
					} else {
						break;
					}
				}
			}

			if ( count+1 == 5 ) {
				return turn;
			}
		}

		return 0;
	}

	@FXML
	private void initialize() {
		for ( int i=0; i<columnMax; i++ ) {
			for ( int j=0; j<rowMax; j++ ) {
				ImageView imageView = new ImageView(new Image("marubatsu/empty.gif"));
				imageView.setFitWidth(32);
				imageView.setFitHeight(32);
				final int m = i;
				final int n = j;
				imageView.setOnMouseClicked(event->{
					if ( board[m][n] == 0 && winner == 0 ) {
//						System.out.println(m + "," + n);
						if ( turn == 1 ) {
							board[m][n] = 1;
							imageView.setImage(new Image("marubatsu/landolt.png"));
						} else if ( turn == 2 ) {
							board[m][n] = 2;
							imageView.setImage(new Image("marubatsu/batsu.jpg"));
						}
						if ( judge(m, n, turn) == turn ) {
							System.out.println(turn + ": 勝ったぜ。");
							winner = turn;
						}
						turn = turn % 2 + 1;
					}
				});
				imageView.setStyle("-fx-alignment:center;");
				gridPane.add(imageView, j, i);
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
