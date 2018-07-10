package rpg.event;

import rpg.Unit;

public abstract class CommandEvent implements Event{
	protected Unit recieve;
	public void setRecieve(Unit r){
		recieve = r;
	}
	public String getName(){
		return "行動";
	}

    public boolean successReact() {
    	return false;
    }

}
