package tetris2;

import game_ui.Game;
import game_ui.GameApp;
import javafx.scene.canvas.GraphicsContext;

public class TetrisGameApp extends GameApp {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected Game createGame(GraphicsContext gc) {
		return new TetrisGame(gc);
	}
}
