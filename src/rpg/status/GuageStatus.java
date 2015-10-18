package rpg.status;




public class GuageStatus extends Status{
    private int maxValue;

    public GuageStatus(int value) {
        super(value);
        setMaxValue(value);
        this.setValue(value);
    }

    public final void setMaxValue(int maxValue){
        this.maxValue = maxValue;
    }

    public final int getMaxValue(){
        return maxValue;
    }

    @Override
	protected final void setValue(int value) {
        if(value<0){
            value = 0;
        }else if(value > this.getMaxValue()){
            value = this.getMaxValue();
        }
        super.setValue(value);
    }

    @Override
    public final int getValue() {
        return super.getValue();
    }

}
