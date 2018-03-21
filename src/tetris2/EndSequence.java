package tetris2;

import sequence.Sequence;

public class EndSequence implements Sequence {

	@Override
	public Sequence update() {
		System.exit(0);
		return null;
	}

}
