package game_ui;

import sequence.CarGameTitleSequence;
import sequence.Sequence;

public class CarGame extends Game{

	GameApp ga;

	CarGame(GameApp ga){
		this.ga = ga;
	}

	@Override
	public Sequence getPrimarySequence() {
		return new CarGameTitleSequence(ga);
	}


}
