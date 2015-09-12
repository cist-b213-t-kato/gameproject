package tetris;

import game_ui.Game;
import game_ui.GameApp;
import sequence.Sequence;

public class TetrisGame extends Game{

	GameApp ga;

	public TetrisGame(GameApp ga){
		this.ga = ga;
	}

	@Override
	public Sequence getPrimarySequence() {
		return new TetrisMainSequence(ga);
	}


}