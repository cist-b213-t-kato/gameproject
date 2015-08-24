package sequence;

import game_ui.GameApp;
import game_ui.InputKey;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameTitleSequence implements Sequence {

	@Override
	public Sequence update() {
		InputKey inputKey = InputKey.getInstance();

		GameApp.getGC().clearRect(0, 0, 640, 480);
		GameApp.getGC().setFill(Color.BLACK);// 塗りつぶしの色を黒に
		GameApp.getGC().setFont(new Font("Meiryo", 72));// フォントをメイリオのサイズ22に
		GameApp.getGC().fillText("Game  Title  Sequence", 160, 240);

		if (inputKey.checkStateKey(KeyCode.ENTER)) { // ゲーム本編画面へ
			return new GameMainSequence();
		}else{
			return this;
		}
	}

}
