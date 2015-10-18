package rpg;

import rpg.event.Event;

public abstract class CommandEvent implements Event{
	protected Unit recieve;
	public void setRecieve(Unit r){
		recieve = r;
	}
	public String getName(){
		return "行動";
	}

}
