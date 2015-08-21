package rpg;

import sequence.Sequence;

public abstract class Game implements Runnable {

	abstract public Sequence getPrimarySequence();

	@Override
	public void run() {
		Sequence seq = getPrimarySequence();
		while (RPGApp.gameContinue) {
			seq.execute();
			seq = seq.getNextSequence();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
