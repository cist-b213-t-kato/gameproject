package rpg;

import rpg.event.CommandEvent;
import rpg.event.Event;
import rpg.status.Agility;
import rpg.status.HitPoint;
import rpg.status.Power;


public class Unit {

	private CommandEvent commandEvent;
    public final HitPoint hitPoint;
    public final Power power;
    public final Agility agility;
    public String name = "Noname";

    public Unit(String name, HitPoint hp, Power pw, Agility agl){
        this.name = name;
        hitPoint = hp;
        power = pw;
        agility = agl;
    }

    public void setCommand(CommandEvent c) {
    	commandEvent = c;
    	commandEvent.setRecieve(this);
    }

    public void execute() {
    	commandEvent.execute();
    }

    public Unit(String name, int hp, int pw, int agl) {
    	this(name, new HitPoint(hp), new Power(pw), new Agility(agl));
    }

    public void acceptEvent(Event e) {}

    public void causeDamage() {}

    public CommandEvent getCommandEvent() {
    	return commandEvent;
    }

}



