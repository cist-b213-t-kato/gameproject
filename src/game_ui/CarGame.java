package game_ui;

import sequence.CarGameTitleSequence;
import sequence.Sequence;

public class CarGame extends Game{

	@Override
	public Sequence getPrimarySequence() {
		return new CarGameTitleSequence();
	}


}
