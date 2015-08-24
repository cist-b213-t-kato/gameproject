package sequence;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import rpg.RPGApp;
import cargamesample.InputKey;

public class GameMenuSequence implements Sequence {

	@Override
	public Sequence update() {
//		GraphicsContext gc = RPGApp.getGC();
		InputKey inputKey = InputKey.getInstance();
		while(RPGApp.gameContinue){
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					RPGApp.getGC().clearRect(0, 0, 640, 480);
					RPGApp.getGC().setFill(Color.BLACK);//塗りつぶしの色を黒に
					RPGApp.getGC().setFont(new Font("Meiryo", 72));//フォントをメイリオのサイズ22に
					RPGApp.getGC().fillText("Game  Menu  Sequence", 160, 240);
				}
			});
			System.out.println("Game  Menu  Sequence");

			if (inputKey.checkStateKey(KeyCode.SPACE)) { // タイトル画面に行きたい
				return new GameTitleSequence();
			}else if (inputKey.checkStateKey(KeyCode.LEFT)) { // メイン画面に行きたい
				return new GameMainSequence();
			}

			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this;
	}

}
