package cargame;

import game_ui.Game;
import game_ui.GameApp;
import sequence.Sequence;

public class CarGame extends Game{

	GameApp ga;

	public CarGame(GameApp ga){
		this.ga = ga;
	}

	@Override
	public Sequence getPrimarySequence() {
		return new CarGameTitleSequence(ga);
	}


}
