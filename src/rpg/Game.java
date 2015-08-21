package rpg;

import sequence.Sequence;

public abstract class Game implements Runnable {

	abstract public Sequence getPrimarySequence();

	@Override
	public void run() {
		Sequence seq = getPrimarySequence();
		while (RPGApp.gameContinue) {
			try {
				seq.execute();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			seq = seq.getNextSequence();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
