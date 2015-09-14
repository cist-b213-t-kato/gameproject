package tetris;

import game_ui.Game;
import sequence.Sequence;

public class TetrisGame extends Game{

	@Override
	public Sequence getPrimarySequence() {
		return new TetrisTitleSequence();
	}


}