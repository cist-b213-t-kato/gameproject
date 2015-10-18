package tetris;

import game_ui.Game;
import game_ui.GameApp;
import game_ui.InputKey;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisTitleSequence implements Sequence{
	@Override
	public Sequence update() {
		while(true){
			GameApp.getGC().setFont(new Font("Meiryo", 66));
			GameApp.getGC().fillText("てとりす", 250, 400);
			GameApp.getGC().setFont(new Font("Meiryo", 22));
			GameApp.getGC().fillText("スペースキーでスタート！", 250, 400+100);

			if(InputKey.getInstance().checkStateKey(KeyCode.SPACE)){
				return new TetrisMainSequence();
			}

			Game.loopEnd();
		}
	}

}
