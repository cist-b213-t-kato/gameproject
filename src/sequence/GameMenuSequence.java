package sequence;

import game_ui.GameApp;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import cargamesample.InputKey;

public class GameMenuSequence implements Sequence {

	@Override
	public Sequence update() {
		InputKey inputKey = InputKey.getInstance();
		GameApp.getGC().clearRect(0, 0, 640, 480);
		GameApp.getGC().setFill(Color.BLACK);// 塗りつぶしの色を黒に
		GameApp.getGC().setFont(new Font("Meiryo", 72));// フォントをメイリオのサイズ22に
		GameApp.getGC().fillText("Game  Menu  Sequence", 160, 240);

		if (inputKey.checkStateKey(KeyCode.SPACE)) { // タイトル画面に行きたい
			return new GameTitleSequence();
		} else if (inputKey.checkStateKey(KeyCode.LEFT)) { // メイン画面に行きたい
			return new GameMainSequence();
		}else{
			return this;
		}
	}

}
