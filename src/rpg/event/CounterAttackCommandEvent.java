package rpg.event;

import rpg.Unit;

public class CounterAttackCommandEvent extends AttackCommandEvent{

	public CounterAttackCommandEvent(Unit t) {
		super(t);
	}

	@Override
	public String getName(){
		return "カウンター";
	}

	@Override
	public boolean successReact() {
		return true;
	}

}
