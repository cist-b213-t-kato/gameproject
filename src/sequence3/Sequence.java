package sequence3;

public abstract class Sequence {
	private boolean changedSequence;
	private Sequence nextSequence;

	public Sequence() {
		changedSequence = false;
		nextSequence = null;
	}

	protected final void changeSequence( Sequence sequence ) {
		changedSequence = true;
		nextSequence = sequence;
	}

	public final Sequence getNextSequence() {
		return nextSequence;
	}

	public final boolean isChangedSequence() {
		return changedSequence;
	}

	public abstract void update();

	public abstract void draw();

}
