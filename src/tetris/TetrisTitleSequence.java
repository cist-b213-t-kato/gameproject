package tetris;

import game_ui.Game;
import game_ui.Game.InputKey;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisTitleSequence extends Sequence{

	private GraphicsContext gc;

	public TetrisTitleSequence(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public Sequence update() {
		Platform.runLater(() -> {
			gc.clearRect(0, 0, 720, 960);
			gc.setFill(Color.hsb(1.0, 0.0, 0.2));
			gc.setFont(new Font("Meiryo Bold", 66));
			gc.fillText("てとりす", 250, 400);
			gc.setFont(new Font("Meiryo", 22));
			gc.fillText("スペースキーでスタート！", 250, 400+100);
		});
		while(true){
			if(InputKey.getInstance().checkStateKey(KeyCode.SPACE)){
				return new TetrisMainSequence(gc);
			}

			Game.loopEnd();
		}
	}

}
