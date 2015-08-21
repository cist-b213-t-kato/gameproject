package sequence;



public abstract class Sequence {
	private Sequence nextSequence;

	public Sequence() {
//
//		RPGApp.gc.fillText("Sequence", 320, 240);
		nextSequence = this;
	}

	//
	public void execute() {
//		RPGApp.gc.fillText("piyo", 320, 240);
	}

	//
	public final Sequence getNextSequence() {
		return nextSequence;
	}

	// インスタンスがなくなるときにすべき処理
	protected void delete(){}

	// nextSequenceをseqに更新する
	public final void update(Sequence seq) {
		delete();
		nextSequence = seq;
	}

}



