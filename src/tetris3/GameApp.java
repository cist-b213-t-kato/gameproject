package tetris3;

import sequence3.Sequence;

public class GameApp {
	public static void main(String[] args) throws InterruptedException {
		Sequence sequence = new TitleSequence();
		System.out.println(sequence);
		while ( true ) {
			sequence.update();
			if ( sequence.isChangedSequence() ) {
				sequence = sequence.getNextSequence();
				System.out.println(sequence);
			}
			Thread.sleep(1000/60);
		}
	}
}
