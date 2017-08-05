package tetris;

import game_ui.Game;
import javafx.scene.canvas.GraphicsContext;
import sequence.Sequence;

public class TetrisGame extends Game {

	private GraphicsContext gc;

	public TetrisGame(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public Sequence getPrimarySequence() {
		return new TetrisTitleSequence(gc);
	}


}