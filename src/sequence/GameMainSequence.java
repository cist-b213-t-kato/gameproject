package sequence;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import rpg.RPGApp;

public class GameMainSequence extends Sequence {

	@Override
	public void execute() {
		while(RPGApp.gameContinue){
			RPGApp.gc.setFill(Color.WHITE);
			RPGApp.gc.fillRect(0, 0, 640, 480);
			RPGApp.gc.setFill(Color.RED);
			RPGApp.gc.fillText("Game  Main  Sequence", 320, 240);

			if (RPGApp.inputKey.checkStateKey(KeyCode.LEFT)) { // タイトル画面に行きたい
				update(new GameTitleSequence());
				return;
			}else if (RPGApp.inputKey.checkStateKey(KeyCode.RIGHT)) { //メニュー画面に行きたい
				update(new GameMenuSequence());
				return;
			}
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}