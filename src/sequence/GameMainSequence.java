package sequence;

import javafx.scene.paint.Color;
import rpg.RPGApp;

public class GameMainSequence extends Sequence {

	public void execute() {

		RPGApp.gc.setFill(Color.WHITE);
		RPGApp.gc.fillRect(0, 0, 640, 480);
		RPGApp.gc.setFill(Color.RED);
		RPGApp.gc.fillText("Game  Main  Sequence", 320, 240);

		if (false) { // タイトル画面に行きたい
			update(new GameTitleSequence());
		}
	}

}