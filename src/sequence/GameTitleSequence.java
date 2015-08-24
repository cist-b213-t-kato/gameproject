package sequence;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import rpg.RPGApp;
import cargamesample.InputKey;

public class GameTitleSequence implements Sequence {

	@Override
	public Sequence update() {
		InputKey inputKey = InputKey.getInstance();
		while(RPGApp.gameContinue){

			Runnable r = ()->{
				RPGApp.getGC().clearRect(0, 0, 640, 480);
				RPGApp.getGC().setFill(Color.BLACK);//塗りつぶしの色を黒に
				RPGApp.getGC().setFont(new Font("Meiryo", 72));//フォントをメイリオのサイズ22に
				RPGApp.getGC().fillText("Game  Title  Sequence", 160, 240);

				System.out.println("Game  Title  Sequence");

				try {
					Thread.sleep(1000/60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};

			if (inputKey.checkStateKey(KeyCode.ENTER)) { // ゲーム本編画面へ
				return new GameMainSequence();
			}

			Platform.runLater(r);
			r.run();
		}
		return this;
	}

}
