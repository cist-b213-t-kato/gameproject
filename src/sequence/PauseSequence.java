package sequence;

import game_ui.InputKey;
import javafx.scene.input.KeyCode;

public class PauseSequence implements Sequence{

	private Sequence parent;

	public PauseSequence(Sequence p){
		parent = p;
	}

	@Override
	public Sequence update() {
		if(InputKey.getInstance().checkStateKey(KeyCode.SPACE)){
			return parent;
		}
		return this;
	}


}
