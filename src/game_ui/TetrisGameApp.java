package game_ui;

import javafx.scene.canvas.GraphicsContext;
import tetris.TetrisGame;

public class TetrisGameApp extends GameApp {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected Game createGame(GraphicsContext gc) {
		return new TetrisGame(gc);
	}
}
