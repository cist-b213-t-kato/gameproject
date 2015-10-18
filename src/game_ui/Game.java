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
			Game.loopEnd();
		}

	}

	private static int count = 0;

	public static int getCount(){
		return count;
	}

	public static void loopEnd(){
		++count;
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

