package rpg;

import javafx.application.Platform;
import sequence.GameTitleSequence;
import sequence.Sequence;

public class Game implements Runnable {

	@Override
	public void run() {
		Runnable r = new Runnable(){
			Sequence seq = new GameTitleSequence();
			@Override
			public void run() {
				seq = seq.update();
				System.out.println(seq);
			}
		};

		while (RPGApp.gameContinue) {
			Platform.runLater(r);
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
