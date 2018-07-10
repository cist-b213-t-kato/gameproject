package rpg;

public class AttackCommandEvent extends CommandEvent{
	protected Unit target;
	public void setTarget(Unit t){
		target = t;
	}

	public AttackCommandEvent(Unit t){
		setTarget(t);
	}

	@Override
	public String getName(){
		return "攻撃";
	}

	@Override
	public void execute() {
		System.out.println(recieve.name+"の"+this.getName()+"！");
		if(target.getCommandEvent().reactRate() >= Math.random()
				|| ((double)target.agility.getValue()/recieve.agility.getValue())*0.25>=Math.random()){
			System.out.println(target.name+"は回避！");
		}else{
			int damage = recieve.power.getValue();
			target.hitPoint.decrease(damage);
			System.out.println(target.name+"は"+damage+"のダメージをうけた");
			target.causeDamage();
		}

	}


}
