package game_ui;

import sequence.Sequence;

public abstract class Game implements Runnable {

	public abstract Sequence getPrimarySequence();

	@Override
	public void run() {

		Sequence seq = getPrimarySequence();
		while (true) {
			System.out.println(seq);
			seq = seq.update();
			Game.sleep();
		}

	}

	public static void sleep(){
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

