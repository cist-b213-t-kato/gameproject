package rpg.status;

public class HitPoint extends GuageStatus{
    public HitPoint(int value) {
        super(value);
    }

    public void decrease(int v){
    	setValue(getValue()-v);
    }

    public void increase(int v){
    	setValue(getValue()+v);
    }

}
