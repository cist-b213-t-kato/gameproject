package tetris2;

import game_ui.Game;
import game_ui.AbstractGameApp;
import javafx.scene.canvas.GraphicsContext;

public class TetrisGameApp extends AbstractGameApp {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected Game createGame(GraphicsContext gc) {
		return new TetrisGame(gc);
	}
}
