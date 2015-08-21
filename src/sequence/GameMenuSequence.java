package sequence;

public class GameMenuSequence extends Sequence {
	public void execute() {
		if (false) { // タイトル画面に行きたい
			update(new GameTitleSequence());
		}
	}

}
