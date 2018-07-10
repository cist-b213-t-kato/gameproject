package cargame;

import game_ui.Game;
import game_ui.AbstractGameApp;
import sequence.Sequence;

public class CarGame extends Game{

	AbstractGameApp ga;

	public CarGame(AbstractGameApp ga){
		this.ga = ga;
	}

	@Override
	public Sequence getPrimarySequence() {
		return new CarGameTitleSequence(ga);
	}


}
