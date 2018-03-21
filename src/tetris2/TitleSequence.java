package tetris2;


import game_ui.Game;
import sequence.Sequence;

public abstract class TitleSequence implements Sequence {

	protected enum Status {
		NONE,
		START,
		;
	}

	@Override
	public Sequence update() {

		while(true){
			draw();
			switch ( controle() ) {
			case START:
				return nextSequence();
			case NONE:
			}

			Game.loopEnd();
		}

	}

	public abstract Status controle();

	public abstract void draw();

	public abstract Sequence nextSequence();

}
