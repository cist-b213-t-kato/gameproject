package sequence;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import rpg.RPGApp;

public class GameTitleSequence extends Sequence {
	public void execute() throws InterruptedException {

		while(RPGApp.gameContinue){

			RPGApp.gc.setFill(Color.WHITE);
			RPGApp.gc.fillRect(0, 0, 640, 480);
			RPGApp.gc.setFill(Color.RED);
			RPGApp.gc.fillText("Game", 320, 240);
			RPGApp.gc.fillText("Game  Title  Sequence", 320, 240);

			if (RPGApp.inputKey.checkStateKey(KeyCode.ENTER)) { // ゲーム本編画面へ
				update(new GameMainSequence());
				return;
			}

			Thread.sleep(1000/60);
		}
	}

}
