package rpg;

public class CounterAttackCommandEvent extends AttackCommandEvent{

	public CounterAttackCommandEvent(Unit t) {
		super(t);
	}

	@Override
	public String getName(){
		return "カウンター";
	}

	@Override
	public double reactRate() {
		return 1.0;
	}

}
