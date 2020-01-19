package tetris;

import game_ui.Game;
import game_ui.Game.InputKey;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisTitleSequence implements Sequence {

	public Sequence next() {
		return new TetrisMainSequence();
	}

	public void draw() {

	}

	@Override
	public Sequence update() {
		draw();
		while(true){
			if(InputKey.getInstance().isPushed(KeyCode.SPACE)){
				return next();
			}

			Game.loopEnd();
		}
	}

}
