package tetris2;

import game_ui.Game.InputKey;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sequence.Sequence;

public class FXTitleSequence extends TitleSequence {

	private GraphicsContext gc;

	public FXTitleSequence(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public void draw() {
		Platform.runLater(() -> {
			gc.clearRect(0, 0, 720, 960);
			gc.setFill(Color.hsb(1.0, 0.0, 0.2));
			gc.setFont(new Font("Meiryo Bold", 66));
			gc.fillText("てとりす", 250, 400);
			gc.setFont(new Font("Meiryo", 22));
			gc.fillText("スペースキーでスタート！", 250, 400+100);
		});
	}

	@Override
	public Sequence nextSequence() {
		return new MainSequence();
	}

	@Override
	public Status controle() {
		if ( InputKey.getInstance().isPushed(KeyCode.SPACE) ) {
			return Status.START;
		}
		return Status.NONE;
	}

}
