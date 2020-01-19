package tetris;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sequence.Sequence;

public class TetrisTitleSequenceEx extends TetrisTitleSequence {

	private GraphicsContext gc;

	public TetrisTitleSequenceEx(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public Sequence next() {
		return new TetrisMainSequenceEx(gc);
	}

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
}
