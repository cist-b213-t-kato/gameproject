package game_ui;

import sequence.Sequence;

public abstract class Game implements Runnable {

	public static Boolean loopFlag = true;

	public abstract Sequence getPrimarySequence();

	@Override
	public void run() {
//		Runnable r = new Runnable(){
//			Sequence seq = getPrimarySequence();
//			@Override
//			public void run() {
//				seq = seq.update();
//				System.out.println(seq);
//			}
//		};

		Sequence seq = getPrimarySequence();
		while (loopFlag) {
			seq = seq.update();
			System.out.println(seq);
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

