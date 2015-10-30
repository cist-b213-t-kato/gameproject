package tetris;

import game_ui.Game;
import game_ui.Game.InputKey;
import game_ui.GameApp;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisTitleSequence implements Sequence{
	@Override
	public Sequence update() {
		Platform.runLater(() -> {
			GameApp.getGC().clearRect(0, 0, 720, 960);
			GameApp.getGC().setFill(Color.hsb(1.0, 0.0, 0.2));
			GameApp.getGC().setFont(new Font("Meiryo Bold", 66));
			GameApp.getGC().fillText("てとりす", 250, 400);
			GameApp.getGC().setFont(new Font("Meiryo", 22));
			GameApp.getGC().fillText("スペースキーでスタート！", 250, 400+100);
		});
		while(true){
			if(InputKey.getInstance().checkStateKey(KeyCode.SPACE)){
				return new TetrisMainSequence();
			}

			Game.loopEnd();
		}
	}

}
