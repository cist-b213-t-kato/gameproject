package game_ui;

import javafx.application.Platform;
import sequence.Sequence;

public abstract class Game implements Runnable {

	static Boolean loopFlag = true;

	public abstract Sequence getPrimarySequence();

	@Override
	public void run() {
		Runnable r = new Runnable(){
			Sequence seq = getPrimarySequence();
			@Override
			public void run() {
				seq = seq.update();
				System.out.println(seq);
			}
		};

		while (loopFlag) {
			Platform.runLater(r);
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
