package tetris;

import game_ui.Game;
import game_ui.GameApp;
import game_ui.InputKey;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import sequence.Sequence;
import tetris.BlockCell.BlockCellColor;

public class TetrisMainSequence implements Sequence {

	GameApp ga;

	BlockCell[][] board = new BlockCell[10][20];

	public TetrisMainSequence(GameApp ga) {
		this.ga = ga;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				board[i][j] = new BlockCell(0, 0);
				board[i][j].setColor(BlockCellColor.NONE);
			}
		}

	}

	int x = 0, y = -1;
	int x2 = 3, y2 = 3;

	@Override
	public Sequence update() {

		// board[4][3].rect.setFill(Color.hsb(64, 0.5, 0.8));
		// board[3][3].rect.setFill(Color.hsb(64, 0.5, 0.8));
		// board[2][3].rect.setFill(Color.hsb(64, 0.5, 0.8));
		// board[3][2].rect.setFill(Color.hsb(64, 0.5, 0.8));

		BlockCell b = new BlockCell(0,0);
		b.setColor(BlockCellColor.RED);
		InputKey inputKey = InputKey.getInstance();
		while (Game.loopFlag) {

//			System.out.println("loop");
			if (inputKey.checkStateKey(KeyCode.LEFT)) {
				x2 = x2 - 1;
			}
			if (inputKey.checkStateKey(KeyCode.RIGHT)) {
				x2 = x2 + 1;
			}
			if(inputKey.checkStateKey(KeyCode.DOWN)){
				y2 = y2 + 1;
			}
			if(inputKey.checkStateKey(KeyCode.UP)){
				y2 = y2 - 1;
			}
			if(inputKey.checkStateKey(KeyCode.SPACE)){
				int xt = x, yt = y;

				x = yt;
				y = -xt;
			}

			Platform.runLater(() -> {
//				System.out.println("runLater");
				GameApp.getGC().clearRect(0, 0, 720, 960);

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 20; j++) {
						Shape rect = board[i][j].rect;
						ga.getGC().setFill(rect.getFill());
						ga.getGC().fillRect(rect.getLayoutX() + 34 * i + 100,
								rect.getLayoutY() + 34 * j + 100, rect.getScaleX(),
								rect.getScaleY());
					}
				}
				Shape rect = b.rect;
				ga.getGC().setFill(rect.getFill());
				ga.getGC().fillRect(rect.getLayoutX() + 34 * (x2+3+0) + 100,
						rect.getLayoutY() + 34 * (y2+3+0) + 100, rect.getScaleX(),
						rect.getScaleY());
				ga.getGC().fillRect(rect.getLayoutX() + 34 * (x+x2+3+0) + 100,
						rect.getLayoutY() + 34 * (y+y2+3+0) + 100, rect.getScaleX(),
						rect.getScaleY());
			});

			Game.sleep();

		}

		return this;
	}

}
